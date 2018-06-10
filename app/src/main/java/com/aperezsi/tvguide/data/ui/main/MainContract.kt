package com.aperezsi.tvguide.data.ui.main

import android.content.Context
import android.content.Intent
import android.support.design.widget.NavigationView
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter>, NavigationView.OnNavigationItemSelectedListener {
        fun getContext() : Context
        fun getActivity() : MainActivity
        fun setPrograms()
        fun attachDrawerLayout()
        fun customizeSearchView()
        fun initListeners()
        fun refreshAdapter()
        fun refreshUser()
        fun updateUI(data: Intent)
    }

    interface Presenter : BasePresenter {
        fun setProgramsList(nowPrograms: APIResponse)
        fun filterSuggestions(newQuery: String) : List<ProgramResponse>
        fun refreshUser()
        fun getActivity() : MainActivity
        fun saveUserToPreferences(key: String)
        fun checkIfUserIsLogged()
    }
}