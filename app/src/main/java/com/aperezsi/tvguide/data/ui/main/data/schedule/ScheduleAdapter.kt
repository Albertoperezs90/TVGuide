package com.aperezsi.tvguide.data.ui.main.data.schedule

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.data.ScheduleProgramming
import com.aperezsi.tvguide.data.ui.channel.ChannelActivity
import com.aperezsi.tvguide.data.ui.detail.DetailActivity
import com.aperezsi.tvguide.data.utils.Constants
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_schedule_row.view.*
import kotlinx.android.synthetic.main.fragment_schedule_row_single.view.*
import kotlinx.android.synthetic.main.fragment_tomorrow_row.view.*

class ScheduleAdapter (val context: Context,
                       val layout: Int,
                       val dataList: MutableList<ScheduleProgramming>) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    private val TAG = "ScheduleAdapter"

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


    inner class ViewHolder(val viewLayout: View, context: Context) : RecyclerView.ViewHolder(viewLayout){
        fun bind(dataItem: ScheduleProgramming){
            val layout = itemView.dealsView
            itemView.dealsView.removeAllViews()
            dataItem.scheduleProgramming.forEach {
                val inflater = LayoutInflater.from(context)
                val view : View = inflater.inflate(R.layout.fragment_schedule_row_single, layout, false)

                if (!it.Image.isNullOrEmpty()){
                    Picasso.get().load(it.Image).into(view.ivProgramSchedule)
                }else {
                    view.ivProgramSchedule.setImageResource(R.drawable.no_image)
                }

                view.tvTitleProgramSchedule.text = it.Title
                layout.addView(view)
            }
        }
    }

}