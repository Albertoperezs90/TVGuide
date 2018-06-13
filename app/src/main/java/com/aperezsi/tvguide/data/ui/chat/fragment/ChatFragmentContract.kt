package com.aperezsi.tvguide.data.ui.chat.fragment

import android.support.v4.app.FragmentActivity
import com.aperezsi.tvguide.data.data.Chat
import com.aperezsi.tvguide.data.data.Message
import com.aperezsi.tvguide.data.data.Messages
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView
import com.aperezsi.tvguide.data.ui.chat.data.ChatAdapter

interface ChatFragmentContract {

    interface View : BaseView<Presenter>{
        fun getChat() : Chat
        fun getFragmentActivity() : FragmentActivity
        fun updateAdapter()
        fun attachAdapter(adapter: ChatAdapter)
        fun getRecyclerLayout() : Int
        fun postMessage(message: Message)
    }

    interface Presenter : BasePresenter {
        fun getUser()
        fun setUser(user: User)
        fun updateChat(chat: Chat, messages: MutableList<Message>)
        fun buildAdapter()
        fun setChat(chat: Chat)
        fun postMessage(message: Message)
    }
}