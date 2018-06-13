package com.aperezsi.tvguide.data.ui.chat.fragment

import android.annotation.SuppressLint
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.chat.data.ChatAdapter
import com.aperezsi.tvguide.data.data.Chat
import com.aperezsi.tvguide.data.data.Message
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.FirebaseService
import com.aperezsi.tvguide.data.service.Storage
import kotlinx.android.synthetic.main.fragment_chat_program.*
import android.app.Activity
import android.support.v7.widget.RecyclerView
import android.view.inputmethod.InputMethodManager


@SuppressLint("ValidFragment")
class ChatFragment (private val chat: Chat) : BaseFragment(), ChatFragmentContract.View {


    private val chatFragmentPresenter = ChatFragmentPresenter(this)
    private lateinit var adapter : ChatAdapter

    override fun getLayout(): Int = R.layout.fragment_chat_program
    override fun getChat(): Chat = chat
    override fun getFragmentActivity(): FragmentActivity = activity!!

    override fun onStart() {
        chatFragmentPresenter.getUser()

        ivSendChat.setOnClickListener {
            val message = Message("", User(), etChatMessage.text.toString())
            chatFragmentPresenter.postMessage(message)
            etChatMessage.setText("")
            hideKeyboard()
        }

        super.onStart()
    }

    override fun getRecyclerLayout(): Int {
        return R.layout.activity_chat_program_dialog
    }

    override fun updateAdapter() {
        this.adapter.notifyDataSetChanged()
    }

    override fun attachAdapter(adapter: ChatAdapter) {
        this.adapter = adapter
        rvChat.layoutManager = LinearLayoutManager(context)
        rvChat.adapter = this.adapter

        val linearLayoutManager = LinearLayoutManager(activity!!)

        this.adapter.registerAdapterDataObserver(object: RecyclerView.AdapterDataObserver() {
            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                linearLayoutManager.smoothScrollToPosition(rvChat, null, adapter.itemCount)
            }
        })
    }

    override fun refreshAdapter(programs: List<ProgramResponse>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun postMessage(message: Message) {
        chatFragmentPresenter.postMessage(message)
    }

    private fun hideKeyboard(){
        val inputMethodManager = activity!!.getSystemService(
                Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
                activity!!.getCurrentFocus().windowToken, 0)
    }
}