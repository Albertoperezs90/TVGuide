package com.aperezsi.tvguide.data.ui.detail.fragment

import android.graphics.BitmapFactory
import android.support.v7.graphics.Palette
import android.view.Menu
import android.widget.ImageView
import com.aperezsi.tvguide.R

class DetailFragmentPresenter (val detailFragment: DetailFragmentContract.View) : DetailFragmentContract.Presenter {

    private var isAppbarExpanded: Boolean = false
    private lateinit var collapsedMenu: Menu

    override fun isAppbarExpanded(): Boolean = isAppbarExpanded
    override fun getMenu(): Menu = collapsedMenu

    override fun setAppbarExpanded(expanded: Boolean) {
        isAppbarExpanded=expanded
    }

    override fun saveMenu(menu: Menu) {
        collapsedMenu = menu
    }

    override fun getPaletteColor(header: ImageView) {
        val bitmap = BitmapFactory.decodeResource(detailFragment.getFragmentActivity().resources, R.drawable.logo)
        Palette.from(bitmap).generate {palette ->
            detailFragment.setCollapsingBarColor(palette.getMutedColor(R.attr.colorPrimary))
        }
    }
}