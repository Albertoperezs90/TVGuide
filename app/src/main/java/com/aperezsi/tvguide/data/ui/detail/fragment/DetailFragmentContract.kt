package com.aperezsi.tvguide.data.ui.detail.fragment

import android.app.Activity
import android.content.Context
import android.support.v4.app.FragmentActivity
import android.view.Menu
import android.widget.ImageView
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface DetailFragmentContract {

    interface View : BaseView<Presenter>{
        fun getFragmentContext() : Context
        fun getFragmentActivity() : FragmentActivity
        fun setCollapsingBarColor(color: Int)
    }

    interface Presenter : BasePresenter {
        fun setAppbarExpanded(expanded: Boolean)
        fun isAppbarExpanded() : Boolean
        fun saveMenu(menu: Menu)
        fun getMenu() : Menu
        fun getPaletteColor(header: ImageView)
    }
}