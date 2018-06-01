package com.aperezsi.tvguide.data.ui.main

import android.content.Context
import android.support.design.widget.NavigationView
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter>, NavigationView.OnNavigationItemSelectedListener {
        fun getContext() : Context
        fun setPrograms()
        fun attachDrawerLayout()
        fun customizeSearchView()
        fun initListeners()
        fun refreshAdapter()
    }

    interface Presenter : BasePresenter {
        fun setProgramsList(nowPrograms: APIResponse)
        fun filterSuggestions(newQuery: String) : List<ProgramResponse>
    }
}