package com.aperezsi.tvguide.data.ui.channel.fragment

import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface ChannelFragmentContract {

    interface View : BaseView<Presenter>{

    }

    interface Presenter : BasePresenter{

    }
}