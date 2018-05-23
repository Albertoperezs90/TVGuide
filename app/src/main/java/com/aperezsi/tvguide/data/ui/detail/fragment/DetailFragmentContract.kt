package com.aperezsi.tvguide.data.ui.detail.fragment

import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface DetailFragmentContract {

    interface View : BaseView<Presenter>{

    }

    interface Presenter : BasePresenter {

    }
}