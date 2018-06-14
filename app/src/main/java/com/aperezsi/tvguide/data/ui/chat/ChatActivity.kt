package com.aperezsi.tvguide.data.ui.chat

import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.Chat
import com.aperezsi.tvguide.data.data.Message
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.Storage
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chat_program.*
import kotlinx.android.synthetic.main.fragment_chat_program.*

class ChatActivity : BaseActivity(), ChatContract.View {

    private val chatPresenter = ChatPresenter(this)

    override fun getContentResource(): Int = R.layout.activity_chat_program
    override fun setFragmentNavigation() = chatPresenter.setNavigation(supportFragmentManager)
    override fun getChat(): Chat = intent.getSerializableExtra("chat") as Chat

    override fun onStart() {
        val program = intent.getSerializableExtra("program") as ProgramResponse
        tvChat.text = program.Title
        if (!program.Image.isNullOrEmpty()){
            Picasso.get().load(program.Image).into(ivChat)
        }
        super.onStart()
    }

}