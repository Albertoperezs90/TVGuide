package com.aperezsi.tvguide.data.ui.main

import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {

    }
}