package com.aperezsi.tvguide.data.ui.channel.data.tomorrow2

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.detail.DetailActivity
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_channel.view.*
import kotlinx.android.synthetic.main.fragment_channel_row.view.*
import kotlinx.android.synthetic.main.fragment_now_row.view.*

class Tomorrow2ChannelAdapter (val context: Context,
                               val layout: Int,
                               val dataList: List<ProgramResponse>) : RecyclerView.Adapter<Tomorrow2ChannelAdapter.ViewHolder>() {
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewLayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewLayout, context)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }


    inner class ViewHolder(viewLayout: View, context: Context) : RecyclerView.ViewHolder(viewLayout){
        fun bind(dataItem: ProgramResponse){
            if (dataItem.Image.isNullOrEmpty()){
                itemView.ivFragmentChannelRow.setImageResource(R.drawable.no_image)
            }else {
                Picasso.get().load(dataItem.Image).into(itemView.ivFragmentChannelRow)
            }

            itemView.tvFragmentChannelTitleRow.text = dataItem.Title
            itemView.tvFragmentChannelEpochStartRow.text = TimeHelper().epochToStringDate(dataItem.EpochStart!!, "HH:mm")
            itemView.tvFragmentChannelCategoryRow.text = dataItem.Category

            itemView.cardViewChannelRow.setOnClickListener { loadDetailProgram(dataItem) }
        }

        private fun loadDetailProgram(dataItem: ProgramResponse) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("program", Gson().toJson(dataItem))
            context.startActivity(intent)
        }
    }

}