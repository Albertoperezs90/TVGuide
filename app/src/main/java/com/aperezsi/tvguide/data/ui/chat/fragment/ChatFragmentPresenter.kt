package com.aperezsi.tvguide.data.ui.chat.fragment

import android.util.Log
import com.aperezsi.tvguide.data.data.Chat
import com.aperezsi.tvguide.data.data.Message
import com.aperezsi.tvguide.data.data.Messages
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.FirebaseChat
import com.aperezsi.tvguide.data.service.Storage
import com.aperezsi.tvguide.data.ui.chat.data.ChatAdapter

class ChatFragmentPresenter (val chatFragmentView: ChatFragmentContract.View) : ChatFragmentContract.Presenter {

    private lateinit var chat: Chat
    private lateinit var currentUser: User
    private lateinit var messages: MutableList<Message>

    override fun getUser() {
        val idUser = Storage(chatFragmentView.getFragmentActivity()).isUserLogged()
        FirebaseChat(this).getUser(idUser)
    }

    override fun setUser(user: User) {
        currentUser = user
        FirebaseChat(this).getChat(chatFragmentView.getChat())
    }

    override fun setChat(chat: Chat) {
        this.chat = chat
        this.messages = chat.messages.values.toMutableList()
        buildAdapter()
        chatFragmentView.initListeners()
    }

    override fun updateChat(chat: Chat, messages: MutableList<Message>) {
        if (!this::chat.isInitialized){
            setChat(chat)
        }

        this.messages.clear()
        this.messages.addAll(messages)
        chatFragmentView.updateAdapter()
    }


    override fun buildAdapter() {
        val adapter = ChatAdapter(chatFragmentView.getFragmentActivity().baseContext,
                                  chatFragmentView.getRecyclerLayout(),
                                  messages,
                                  currentUser.id)
        chatFragmentView.attachAdapter(adapter)
    }

    override fun postMessage(message: Message) {
        message.user = currentUser
        FirebaseChat(this).postMessage(message, chat)
    }

}