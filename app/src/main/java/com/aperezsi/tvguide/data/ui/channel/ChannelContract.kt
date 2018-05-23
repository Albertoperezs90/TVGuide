package com.aperezsi.tvguide.data.ui.channel

import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface ChannelContract {

    interface View : BaseView<Presenter>{

    }

    interface Presenter : BasePresenter {

    }
}