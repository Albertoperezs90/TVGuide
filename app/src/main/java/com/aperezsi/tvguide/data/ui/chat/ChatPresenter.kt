package com.aperezsi.tvguide.data.ui.chat

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.Message
import com.aperezsi.tvguide.data.ui.chat.fragment.ChatFragment
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation

class ChatPresenter (val chatView: ChatContract.View) : ChatContract.Presenter, FragmentNavigation.Presenter {

    private var fragment: ChatFragment? = null

    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        fragment = ChatFragment(chatView.getChat())
        fragmentManager.beginTransaction().add(R.id.chat_program_layout, fragment).commit()
    }

    override fun sendMessage(message: Message) {
        fragment!!.postMessage(message)
    }

}