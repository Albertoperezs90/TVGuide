package com.aperezsi.tvguide.data.ui.detail.fragment

import android.app.Activity
import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.v4.app.FragmentActivity
import android.view.Menu
import android.widget.ImageView
import android.widget.Toolbar
import com.aperezsi.tvguide.data.data.Chat
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface DetailFragmentContract {

    interface View : BaseView<Presenter>{
        fun initAttributes()
        fun initListeners()
        fun getFragmentContext() : Context
        fun getFragmentActivity() : FragmentActivity
        fun setCollapsingBarColor(color: Int)
        fun AppBarLayout.setOffSetChangeListener()
        fun startChat(chat: Chat)
        fun showToast(message: String)
    }

    interface Presenter : BasePresenter {
        fun setAppbarExpanded(expanded: Boolean)
        fun isAppbarExpanded() : Boolean
        fun saveMenu(menu: Menu)
        fun getMenu() : Menu
        fun getPaletteColor(header: ImageView)
        fun setSupportActionBar(toolbar: android.support.v7.widget.Toolbar)
        fun setProgram(program: ProgramResponse)
        fun getProgram() : ProgramResponse
        fun openChat()
    }
}