package com.aperezsi.tvguide.data.ui.main.fragment.now

import android.app.Activity
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.main.data.NowAdapter
import com.aperezsi.tvguide.data.ui.main.data.NowRepository
import com.aperezsi.tvguide.data.ui.main.data.ProgramRepository

/**
 * Created by alberto on 06/05/2018.
 */
class NowPresenter (val nowView: NowContract.View) : NowContract.Presenter {

    var nowPrograms: List<ProgramResponse>? = null
    val nowRepository: NowRepository = NowRepository(this)

    override fun buildAdapter(layout: Int) {
        nowPrograms = nowView.getNowPrograms()
        val adapter = NowAdapter(nowView.getFragmentContext(), layout, filterNowPrograms()!!)
        nowView.attachAdapter(adapter)
        nowView.notifyDataAdapterChanged()
    }

    override fun filterNowPrograms(): List<ProgramResponse>? {
        var filteredList: List<ProgramResponse>? = null
        filteredList = nowPrograms!!.distinctBy { it.IdChannel }.toMutableList()
        return filteredList
    }

    override fun refreshPrograms() {
        nowRepository.refreshPrograms()
    }


    override fun updatePrograms(programs: List<ProgramResponse>?) {
        nowPrograms = programs
        nowView.notifyDataAdapterChanged()
        nowView.setContainerRefresh(false)
    }



}