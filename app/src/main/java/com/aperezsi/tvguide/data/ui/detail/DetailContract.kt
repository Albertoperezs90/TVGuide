package com.aperezsi.tvguide.data.ui.detail

import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface DetailContract {

    interface View : BaseView<Presenter>{

    }

    interface Presenter : BasePresenter {

    }
}