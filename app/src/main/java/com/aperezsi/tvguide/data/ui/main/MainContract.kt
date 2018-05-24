package com.aperezsi.tvguide.data.ui.main

import android.content.Context
import android.media.tv.TvContract
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView
import com.aperezsi.tvguide.data.ui.main.data.NowAdapter
import java.io.Serializable

interface MainContract {

    interface View : BaseView<Presenter> {
        fun getContext() : Context
        fun setPrograms()
        fun attachDrawerLayout()
    }

    interface Presenter : BasePresenter {
        fun setProgramsList(nowPrograms: APIResponse)
        fun filterSuggestions(oldQuery: String, newQuery: String) : MutableList<ProgramResponse>
    }
}