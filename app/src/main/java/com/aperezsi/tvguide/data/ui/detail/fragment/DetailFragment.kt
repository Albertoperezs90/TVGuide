package com.aperezsi.tvguide.data.ui.detail.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.support.design.widget.AppBarLayout
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper
import kotlinx.android.synthetic.main.fragment_detail.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL

@SuppressLint("ValidFragment")
class DetailFragment (private val program: ProgramResponse) : BaseFragment(), DetailFragmentContract.View {

    private val detailFragmentPresenter = DetailFragmentPresenter(this)

    override fun getLayout(): Int = R.layout.fragment_detail
    override fun getFragmentContext(): Context = context!!
    override fun getFragmentActivity(): FragmentActivity = activity!!

    override fun onStart() {
        super.onStart()
        detailFragmentPresenter.setProgram(program)
        detailFragmentPresenter.getPaletteColor(header)
        detailFragmentPresenter.setSupportActionBar(anim_toolbar)
        initAttributes()
        initListeners()
    }

    override fun initAttributes() {
        collapsing_toolbar.title = detailFragmentPresenter.getProgram().Title

        if (program.Image.isNullOrEmpty()){
            header.setImageResource(R.drawable.no_image)
        }else {
            doAsync {
                val bitmap = BitmapFactory.decodeStream(URL(detailFragmentPresenter.getProgram().Image).openStream())
                uiThread {
                    header.setImageBitmap(bitmap)
                }
            }
        }

        val epochStart = detailFragmentPresenter.getProgram().EpochStart
        val epochEnd = detailFragmentPresenter.getProgram().EpochEnd
        horaI.text = TimeHelper().epochToStringDate(epochStart!!, "HH:mm")
        horaF.text = TimeHelper().epochToStringDate(epochEnd!!, "HH:mm")
        progressBarHorizontal.progress = TimeHelper().getCurrentPercentage(
                epochStart.toLong(),
                epochEnd.toLong()
        )

        type.text = detailFragmentPresenter.getProgram().Category
        description.text = detailFragmentPresenter.getProgram().Description
    }

    override fun initListeners() {
        appbar.setOffSetChangeListener()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_main, menu)
        detailFragmentPresenter.saveMenu(menu!!)
    }

    override fun onPrepareOptionsMenu(menu: Menu?) {
        if (!detailFragmentPresenter.isAppbarExpanded() || detailFragmentPresenter.getMenu().size() != 1) {
            detailFragmentPresenter.getMenu().add("Chat")
                    .setIcon(R.drawable.ic_chat)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }

        return super.onPrepareOptionsMenu(detailFragmentPresenter.getMenu())
    }

    override fun setCollapsingBarColor(color: Int) {
        collapsing_toolbar.setContentScrimColor(color)
    }

    override fun AppBarLayout.setOffSetChangeListener() {
        this.addOnOffsetChangedListener {appBarLayout, verticalOffset ->
            if (Math.abs(verticalOffset) > 200  ){
                detailFragmentPresenter.setAppbarExpanded(false)
                activity!!.invalidateOptionsMenu()
            }else {
                detailFragmentPresenter.setAppbarExpanded(true)
                activity!!.invalidateOptionsMenu()
            }
        }
    }
}