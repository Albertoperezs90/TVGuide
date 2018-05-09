package com.aperezsi.tvguide.data.ui.main

import android.content.Context
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun getContext() : Context
    }

    interface Presenter : BasePresenter {

    }
}