package com.aperezsi.tvguide.data.ui.channel

import android.content.Context
import com.aperezsi.tvguide.data.data.ChannelProgamming
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface ChannelContract {

    interface View : BaseView<Presenter>{
        fun extractIdChannel() : String
        fun getActivityContext() : Context
        fun setFragmentNavigation()
        fun startProgressView()
        fun endProgressView()
        fun updateFav()
        fun setFav()
    }

    interface Presenter : BasePresenter {
        fun loadData()
        fun refreshDataList(channelProgamming: ChannelProgamming)
    }
}