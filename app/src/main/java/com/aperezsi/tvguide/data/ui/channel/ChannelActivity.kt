package com.aperezsi.tvguide.data.ui.channel

import android.content.Context
import android.os.Bundle
import android.view.View
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.service.Storage
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import com.aperezsi.tvguide.data.ui.base.BaseDelayedActivity
import com.aperezsi.tvguide.data.utils.Constants
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_channel.*

class ChannelActivity : BaseDelayedActivity(), ChannelContract.View {


    private val channelPresenter = ChannelPresenter(this)
    private lateinit var idChannel: String

    override fun getContentResource(): Int = R.layout.activity_channel
    override fun getActivityContext(): Context = this
    override fun setFragmentNavigation() = channelPresenter.setNavigation(supportFragmentManager, tabsChannel, viewpagerChannel)

    override fun onStart() {
        startProgressView()
        channelPresenter.loadData()
        heart_fav.setOnClickListener {
            updateFav()
        }
        setFav()
        super.onStart()
    }
    override fun extractIdChannel(): String {
        idChannel = intent.getStringExtra("idChannel")
        return idChannel
    }

    override fun startProgressView() {
        Picasso.get().load("${Constants.ImageEndPoint}${extractIdChannel()}${Constants.PngExtension}").into(header)
        gray_delayed_view.visibility = View.VISIBLE
        gray_delayed_view.animate().alpha(0.2F)
        channelProgress.isIndeterminate = true
        channelProgress.visibility = View.VISIBLE
    }

    override fun endProgressView() {
        gray_delayed_view.animate().alpha(0.0F)
        gray_delayed_view.visibility = View.GONE
        channelProgress.visibility = View.GONE
    }

    override fun updateFav() {
        if (heart_fav.tag.equals("no_fav")){
            heart_fav.tag = "fav"
            heart_fav.setImageResource(R.drawable.heart_filled)
            Storage(this).saveChannel(idChannel)
        }else {
            heart_fav.setImageResource(R.drawable.heart_empty)
            heart_fav.tag = "no_fav"
            Storage(this).removeIdChannel(idChannel)
        }
    }

    override fun setFav() {
        val list = Storage(this).getIdChannels()
        list.forEach {
            if (it.equals(idChannel)){
                heart_fav.setImageResource(R.drawable.heart_filled)
                heart_fav.tag = "fav"
            }
        }
    }

}