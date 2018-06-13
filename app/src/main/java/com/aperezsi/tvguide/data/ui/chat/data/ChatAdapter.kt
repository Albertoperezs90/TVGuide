package com.aperezsi.tvguide.data.ui.chat.data

import android.content.Context
import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.Chat
import com.aperezsi.tvguide.data.data.Message
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_chat_program_dialog.view.*
import android.view.ViewGroup.LayoutParams.FILL_PARENT
import android.widget.RelativeLayout
import com.aperezsi.tvguide.R.*
import org.jetbrains.anko.alignEnd


class ChatAdapter (val context: Context,
                   val layout: Int,
                   val dataList: MutableList<Message>,
                   val idUser: String) : RecyclerView.Adapter<ChatAdapter.ViewHolder>() {

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
        fun bind(dataItem: Message){
            if (dataItem.user.id == idUser) {
                val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
                params.addRule(RelativeLayout.ALIGN_PARENT_END)
                params.setMargins(0,20,10,0)
                itemView.cardViewChat.layoutParams = params
                itemView.cardViewChat.setCardBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight_Darker))
            }else {
                val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
                params.addRule(RelativeLayout.CENTER_HORIZONTAL)
                params.setMargins(10,20,0,0)
                itemView.cardViewChat.layoutParams = params
            }

            itemView.tvMessageUserChat.text = dataItem.message

            if (!dataItem.user.avatar.isNullOrEmpty()){
                Picasso.get().load(dataItem.user.avatar).into(itemView.ivUserMessageChat)
            }else {
                itemView.ivUserMessageChat.setImageResource(drawable.no_image)
            }
        }
    }

}