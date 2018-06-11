package com.aperezsi.tvguide.data.ui.main

import android.content.Context
import android.content.DialogInterface
import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.view.ViewManager
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.FirebaseService
import com.aperezsi.tvguide.data.utils.adapters.FragmentAdapter
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation
import com.google.android.gms.auth.api.signin.internal.Storage
import org.jetbrains.anko.AlertBuilder
import org.jetbrains.anko.customView

class MainPresenter constructor(val mainView: MainContract.View) : MainContract.Presenter, FragmentNavigation.Presenter {


    var nowPrograms: MutableList<ProgramResponse>? = null
    var originalProgramList: List<ProgramResponse>? = null
    val firebaseService = FirebaseService(this)

    override fun setProgramsList(nowPrograms: APIResponse) {
        this.nowPrograms = nowPrograms.response.toMutableList()
        this.originalProgramList = nowPrograms.response
    }

    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        mainView.setPrograms()
        val adapter = FragmentAdapter(nowPrograms!!.toList(), mainView.getContext(), fragmentManager)
        viewPager!!.adapter = adapter
        tabLayout!!.setupWithViewPager(viewPager)
    }

    override fun filterSuggestions(newQuery: String) : List<ProgramResponse> {
        nowPrograms = nowPrograms!!.filter { it.Title!!.startsWith(newQuery) }.toMutableList()
        mainView.refreshAdapter()
        return nowPrograms!!.toList()
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

    override fun refreshUser() {
        mainView.refreshUser(firebaseService.getCurrentUser())
    }

    override fun logginUser(user: User) {
        firebaseService.logginUser(user)
    }

    override fun createUser(user: User) {
        firebaseService.createUser(user)
    }

    override fun showToast(message: String) {
        mainView.showToast(message)
    }

    override fun alertDismiss() {
        mainView.alertDismiss()
    }
}