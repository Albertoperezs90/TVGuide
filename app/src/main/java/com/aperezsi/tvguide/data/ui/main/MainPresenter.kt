package com.aperezsi.tvguide.data.ui.main

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.util.Base64
import android.util.Log
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.data.ScheduleProgramming
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.FirebaseService
import com.aperezsi.tvguide.data.service.Storage
import com.aperezsi.tvguide.data.service.interfaces.ProgramAPI
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.utils.adapters.FragmentAdapter
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.io.ByteArrayOutputStream

class MainPresenter constructor(val mainView: MainContract.View) : MainContract.Presenter, FragmentNavigation.Presenter {


    var nowPrograms: MutableList<ProgramResponse>? = null
    var originalProgramList: List<ProgramResponse>? = null
    val firebaseService = FirebaseService(this)
    private lateinit var viewPager: ViewPager
    private val scheduleProgramming = mutableListOf<ScheduleProgramming>()

    override fun setProgramsList(nowPrograms: APIResponse) {
        this.nowPrograms = nowPrograms.response.toMutableList()
        this.originalProgramList = nowPrograms.response
    }

    override fun isFirsTime(): Boolean {
        val storage = com.aperezsi.tvguide.data.service.Storage(mainView.getActivity())
        return storage.isFirstTime()
    }

    override fun loadFavourites() {
        val list = this.nowPrograms!!.distinctBy { it.IdChannel }
        val storage =  com.aperezsi.tvguide.data.service.Storage(mainView.getActivity())
        for (i in 0..6){
            storage.saveChannel(list.get(i).IdChannel!!)
        }
    }


    override fun filterPrograms(filter: String) {
        nowPrograms!!.clear()
        when (filter){
            "" -> nowPrograms!!.addAll(originalProgramList!!)
            "fav" -> nowPrograms!!.addAll(getFavouritePrograms())
            else -> nowPrograms!!.addAll(originalProgramList!!.filter { it.Type == filter })
        }
        val position = viewPager.currentItem
        val fragment = (viewPager.adapter as FragmentAdapter).registeredFragments[position] as BaseFragment
        fragment.refreshAdapter(nowPrograms!!)
    }

    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        mainView.setPrograms()
        val adapter = FragmentAdapter(nowPrograms!!, mainView.getContext(), fragmentManager)
        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager)
        this.viewPager = viewPager
    }

    override fun filterSuggestions(newQuery: String) : List<ProgramResponse> {
        nowPrograms!!.clear()
        val filtered = originalProgramList!!.filter { it.Title!!.contains(newQuery) }.toMutableList()
        nowPrograms = filtered

        val position = viewPager.currentItem
        val fragment = (viewPager.adapter as FragmentAdapter).registeredFragments[position] as BaseFragment
        fragment.refreshAdapter(nowPrograms!!)

        return nowPrograms!!
    }

    override fun getActivity(): MainActivity {
        return mainView.getActivity()
    }

    override fun saveUserToPreferences(key: String) {
        com.aperezsi.tvguide.data.service.Storage(mainView.getActivity()).saveUserKey(key)
    }

    override fun checkIfUserIsLogged() {
        val key = com.aperezsi.tvguide.data.service.Storage(mainView.getActivity()).isUserLogged()
        if (!key.isNullOrEmpty()){
            firebaseService.getUserFromDb(key)
        }
    }

    override fun refreshUser(user: User?) {
        mainView.refreshUser(user)
    }

    override fun logginUser(user: User) {
        firebaseService.logginUser(user)
    }

    override fun createUser(user: User) {
        firebaseService.createUser(user)
    }

    override fun logoutUser() {
        firebaseService.logoutUser()
        Storage(mainView.getActivity()).removeUser()
        refreshUser(null)
    }


    override fun showToast(message: String) {
        mainView.showToast(message)
    }

    override fun alertDismiss() {
        mainView.alertDismiss()
    }

    override fun getFavouritePrograms(): MutableList<ProgramResponse> {
        val idChannels = com.aperezsi.tvguide.data.service.Storage(mainView.getActivity()).getIdChannels()
        var favs = mutableListOf<ProgramResponse>()
        idChannels.forEach { fav ->
            favs.addAll(originalProgramList!!
                    .filter { it.IdChannel == fav }
                    .filter { TimeHelper().getCurrentSecondsEpoch() < it.EpochEnd!!.toInt() })
        }
        return favs
    }

    override fun updateUserData(user: User) {
        firebaseService.updateUser(user)
    }

    override fun loadScheduleData() {
        if (scheduleProgramming.isEmpty()){
            val idChannelList = Storage(mainView.getActivity()).getIdChannels()
            val lastChannel = idChannelList.last().toInt()

            idChannelList.forEach { id ->
                ProgramAPI.create().getChannelProgamming(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ apiResponse ->
                            addProgramListToScheduleProgramming(apiResponse.response.subList(0, apiResponse.response.size / 3))
                        }, {
                            error ->
                            //TODO implement logger
                            Log.e("mapProgamResponse ERROR", error.message)
                        })
            }
        }
    }

    private fun addProgramListToScheduleProgramming(programs: List<ProgramResponse>){
        scheduleProgramming.add(ScheduleProgramming(programs.toMutableList()))
        Log.d("PASANDO", "**")
    }

    override fun getScheduleData(): MutableList<ScheduleProgramming> {
        return scheduleProgramming
    }

}