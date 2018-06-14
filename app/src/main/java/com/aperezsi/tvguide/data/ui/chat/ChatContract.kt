package com.aperezsi.tvguide.data.ui.chat

import com.aperezsi.tvguide.data.data.Chat
import com.aperezsi.tvguide.data.data.Message
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface ChatContract {

    interface View : BaseView<Presenter>{
        fun getChat() : Chat
    }

    interface Presenter : BasePresenter {
        fun sendMessage(message: Message)
    }
}