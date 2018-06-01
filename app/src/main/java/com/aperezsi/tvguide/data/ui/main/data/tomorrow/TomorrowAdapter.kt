package com.aperezsi.tvguide.data.ui.main.data.tomorrow

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.channel.ChannelActivity
import com.aperezsi.tvguide.data.ui.detail.DetailActivity
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_tomorrow_row.view.*

class TomorrowAdapter (val context: Context,
                       val layout: Int,
                       val dataList: List<ProgramResponse>) : RecyclerView.Adapter<TomorrowAdapter.ViewHolder>() {

    private val TAG = "TomorrowAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewLayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewLayout, context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList.get(position)
        holder.bind(item)
    }


    inner class ViewHolder(viewLayout: View, context: Context) : RecyclerView.ViewHolder(viewLayout){
        fun bind(dataItem: ProgramResponse){
            Picasso.get().load("http://images.miguia.tv/channels/xhdpi/channel_${dataItem.IdChannel}.png").into(itemView.ivChannelLogoTomorrow)
            itemView.tvTimeStartProgramTomorrow.text = TimeHelper().epochToStringDate(dataItem.EpochStart!!, "HH:mm")
            itemView.tvTitleProgramTomorrow.text = dataItem.Title
            itemView.tvTempCapTomorrow.text = dataItem.Category
            if (dataItem.Image.isNullOrEmpty()){
                itemView.ivProgramTomorrow.setImageResource(R.drawable.no_image)
            }else {
                Picasso.get().load(dataItem.Image).into(itemView.ivProgramTomorrow)
            }

            itemView.ivChannelLogoTomorrow.setOnClickListener { loadCurrentChannel(dataItem.IdChannel) }
            itemView.cardViewTopTomorrow.setOnClickListener { loadDetailProgram(dataItem) }
        }

        private fun loadDetailProgram(dataItem: ProgramResponse) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("program", Gson().toJson(dataItem))
            intent.putExtra("fragment", TAG)
            context.startActivity(intent)
        }

        private fun loadCurrentChannel(idChannel: String?) {
            val intent = Intent(context, ChannelActivity::class.java)
            intent.putExtra("idChannel", idChannel)
            context.startActivity(intent)
        }
    }

}