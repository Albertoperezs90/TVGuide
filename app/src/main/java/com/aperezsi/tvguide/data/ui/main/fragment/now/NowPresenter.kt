package com.aperezsi.tvguide.data.ui.main.fragment.now

import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.service.Storage
import com.aperezsi.tvguide.data.ui.main.data.now.NowAdapter
import com.aperezsi.tvguide.data.ui.main.data.now.NowRepository
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper
import java.sql.Time

/**
 * Created by alberto on 06/05/2018.
 */
class NowPresenter (val nowView: NowContract.View) : NowContract.Presenter {

    var nowPrograms: MutableList<ProgramResponse>? = null
    var originalNowPrograms: List<ProgramResponse>? = null
    val nowRepository: NowRepository = NowRepository(this)

    override fun buildAdapter(layout: Int) {
        nowPrograms = nowView.getNowPrograms().toMutableList()!!
        originalNowPrograms = nowPrograms
        nowPrograms = filterNowPrograms()!!.toMutableList()
        val favourites = Storage(nowView.getFragmentActivity()).getIdChannels()
        val newList = mutableListOf<ProgramResponse>()
        favourites.forEach {favourites ->
            newList.addAll(nowPrograms!!.filter { it.IdChannel == favourites })
        }
        nowPrograms = newList
        val adapter = NowAdapter(nowView.getFragmentContext(), layout, nowPrograms!!)
        nowView.attachAdapter(adapter)
        nowView.notifyDataAdapterChanged()
    }

    override fun filterNowPrograms(): List<ProgramResponse>? {
        var filteredList: List<ProgramResponse>? = null
        filteredList =
                nowPrograms!!
                .filter { TimeHelper().getCurrentSecondsEpoch() < it.EpochEnd!!.toInt() }
                .distinctBy { it.IdChannel }
                .toMutableList()
        return filteredList
    }

    override fun refreshPrograms() {
        nowRepository.refreshPrograms()
    }


    override fun updatePrograms(programs: List<ProgramResponse>?) {
        nowPrograms!!.clear()
        nowPrograms!!.addAll(programs!!)
        val filteredPrograms = filterNowPrograms()
        nowPrograms!!.clear()
        nowPrograms!!.addAll(filteredPrograms!!)
        nowView.notifyDataAdapterChanged()
        nowView.setContainerRefresh(false)
    }



}