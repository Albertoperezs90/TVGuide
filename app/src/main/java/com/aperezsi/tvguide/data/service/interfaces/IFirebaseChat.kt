package com.aperezsi.tvguide.data.service.interfaces

import com.aperezsi.tvguide.data.data.Chat
import com.aperezsi.tvguide.data.data.Message

interface IFirebaseChat {

    fun getUser(key: String)
    fun getChat(chat: Chat)
    fun postMessage(message: Message, chat: Chat)
}