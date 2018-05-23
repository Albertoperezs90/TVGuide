package com.aperezsi.tvguide.data.ui.detail.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.content.Context
import android.view.View
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_now_row.view.*


class DetailAdapter (val context: Context,
                  val layout: Int,
                  val dataItem: ProgramResponse) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewLayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewLayout, context)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataItem
        holder.bind(item)
    }


    inner class ViewHolder(viewLayout: View, context: Context) : RecyclerView.ViewHolder(viewLayout){
        fun bind(dataItem: ProgramResponse){
            for (i in 0..10){
                Picasso.get().load("http://images.miguia.tv/channels/xhdpi/channel_${dataItem.IdChannel}.png").into(itemView.ivChannelLogo)
                itemView.tvTimeStartProgramNow.text = TimeHelper().epochToStringDate(dataItem.EpochStart!!, "HH:mm")
                itemView.tvTitleProgramNow.text = dataItem.Title
                if (dataItem.Image.isNullOrEmpty()){
                    itemView.ivProgramNow.setImageResource(R.drawable.no_image)
                }else {
                    Picasso.get().load(dataItem.Image).into(itemView.ivProgramNow)
                }
            }
        }
    }

}