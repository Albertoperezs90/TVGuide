package com.aperezsi.tvguide.data.ui.main

import com.aperezsi.tvguide.data.utils.BasePresenter
import com.aperezsi.tvguide.data.utils.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {

        fun loadCurrentPrograms()
    }
}