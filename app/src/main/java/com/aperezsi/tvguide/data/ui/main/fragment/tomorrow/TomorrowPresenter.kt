package com.aperezsi.tvguide.data.ui.main.fragment.tomorrow

import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.main.data.tomorrow.TomorrowAdapter
import com.aperezsi.tvguide.data.ui.main.data.tomorrow.TomorrowRepository

class TomorrowPresenter (val tomorrowView: TomorrowContract.View): TomorrowContract.Presenter {

    private val tomorrowRepository: TomorrowRepository = TomorrowRepository(this)
    private var tomorrowPrograms: List<ProgramResponse>? = null

    override fun loadDataIntoPrograms(epoch: String) {
        tomorrowRepository.getTomorrowPrograms(epoch)
    }

    override fun buildAdapter(layout: Int) {
        val adapter = TomorrowAdapter(tomorrowView.getFragmentContext(), layout, tomorrowPrograms!!)
        tomorrowView.attachAdapter(adapter)
        tomorrowView.notifyDataAdapterChanged()
    }


    override fun updatePrograms(programs: List<ProgramResponse>?) {
        if (tomorrowPrograms == null){
            tomorrowPrograms = programs
            tomorrowPrograms = filterTomorrowPrograms()
            tomorrowView.buildAdapter()
        }
        tomorrowPrograms = programs
        tomorrowPrograms = filterTomorrowPrograms()
        tomorrowView.setContainerRefresh(false)
        tomorrowView.notifyDataAdapterChanged()

    }

    override fun filterTomorrowPrograms(): List<ProgramResponse>? {
        var filteredList: List<ProgramResponse>? = null
        filteredList = tomorrowPrograms!!.distinctBy { it.IdChannel }.toMutableList()
        return filteredList
    }

    override fun refreshPrograms(epoch: String) {
        tomorrowRepository.refreshPrograms(epoch)
    }

}