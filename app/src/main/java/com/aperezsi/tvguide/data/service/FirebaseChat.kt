package com.aperezsi.tvguide.data.service

import android.util.Log
import com.aperezsi.tvguide.data.data.Chat
import com.aperezsi.tvguide.data.data.Message
import com.aperezsi.tvguide.data.data.Messages
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.interfaces.IFirebaseChat
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.chat.fragment.ChatFragmentPresenter
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseChat (val presenter: ChatFragmentPresenter) : IFirebaseChat {

    private val dbInstance = FirebaseDatabase.getInstance()

    override fun getUser(key: String) {
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                presenter.setUser(user!!)
            }

            override fun onCancelled(dataError: DatabaseError) {
                Log.e("ERROR", dataError.message)
            }
        }
        val refUsers = dbInstance.getReference("users").child(key).addValueEventListener(listener)
    }

    override fun getChat(chat: Chat)  {
        val ref = dbInstance.getReference("chat").child(chat.id)

        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val messages: MutableList<Message> = mutableListOf()
                val value = dataSnapshot.getValue(Chat::class.java)
                if (value != null){
                    dataSnapshot.children.forEach{
                        if (it.key.equals("messages")){
                            it.children.forEach {
                                messages.add(it.getValue(Message::class.java)!!)
                            }
                        }
                    }
                    presenter.updateChat(value, messages)
                }else {
                    presenter.setChat(createChat(chat))
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.e("FIREBASE_CHAT", "Failed to read value.", error.toException())
            }
        })
    }


    override fun postMessage(message: Message, chat: Chat) {
        val chatRef = dbInstance.getReference("chat").child(chat.id).child("messages")
        val messageId = chatRef.push().key
        message.id = messageId!!
        chatRef.child(messageId!!).setValue(message)
    }

    private fun createChat(chat: Chat) : Chat{
        val chatRef = dbInstance.getReference("chat")
        chatRef.child(chat.id).setValue(chat)
        return chat
    }
}