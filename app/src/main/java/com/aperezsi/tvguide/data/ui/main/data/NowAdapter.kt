package com.aperezsi.tvguide.data.ui.main.data

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import kotlinx.android.synthetic.main.fragment_now_row.view.*

class NowAdapter (val context: Context,
                  val layout: Int,
                  val dataList: APIResponse) : RecyclerView.Adapter<NowAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewLayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewLayout, context)
    }

    override fun getItemCount(): Int {
        return dataList.programsResponse.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList.programsResponse.get(position)
        holder.bind(item)
    }


    inner class ViewHolder(viewLayout: View, context: Context) : RecyclerView.ViewHolder(viewLayout){
        fun bind(dataItem: ProgramResponse){
            itemView.tvTitle.text = dataItem.title
        }
    }

}