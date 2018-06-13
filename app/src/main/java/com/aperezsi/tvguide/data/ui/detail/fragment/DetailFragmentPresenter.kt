package com.aperezsi.tvguide.data.ui.detail.fragment

import android.graphics.BitmapFactory
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.graphics.Palette
import android.view.Menu
import android.widget.ImageView
import android.widget.Toolbar
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.Chat
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.service.Storage
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

class DetailFragmentPresenter (val detailFragment: DetailFragmentContract.View) : DetailFragmentContract.Presenter {

    private var isAppbarExpanded: Boolean = false
    private lateinit var collapsedMenu: Menu
    private lateinit var program: ProgramResponse

    override fun isAppbarExpanded(): Boolean = isAppbarExpanded
    override fun getMenu(): Menu = collapsedMenu
    override fun getProgram(): ProgramResponse = program

    override fun setAppbarExpanded(expanded: Boolean) {
        isAppbarExpanded=expanded
    }

    override fun saveMenu(menu: Menu) {
        collapsedMenu = menu
    }

    override fun getPaletteColor(header: ImageView) {
        doAsync {
            val bitmap = BitmapFactory.decodeStream(URL(program.Image).openStream())
            uiThread {
                Palette.from(bitmap).generate {palette ->
                    detailFragment.setCollapsingBarColor(palette.getMutedColor(R.attr.colorPrimary))
                }
            }
        }
    }

    override fun setSupportActionBar(toolbar: android.support.v7.widget.Toolbar) {
        (detailFragment.getFragmentActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        detailFragment.getFragmentActivity().invalidateOptionsMenu()
    }


    override fun setProgram(program: ProgramResponse) {
        this.program = program
    }

    override fun openChat() {
        val userId = Storage(detailFragment.getFragmentActivity()).isUserLogged()
        if (!userId.isNullOrEmpty()){
            val programId = program.Id
            val epochEnd = program.EpochEnd
            val chat: Chat = Chat(programId+epochEnd,programId!!, epochEnd!!)
            detailFragment.startChat(chat)
        }else {
            detailFragment.showToast("Inicie sesión para poder participar en la comunidad")
        }
    }
}