package com.aperezsi.tvguide.data.service

import android.app.Activity
import android.content.Context
import com.aperezsi.tvguide.data.service.interfaces.IStorage

class Storage (activity: Activity) : IStorage {


    private val sharedPreferences = activity.getSharedPreferences("teletexto", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    override fun saveUserKey(key: String) {
        editor.putString("user", key)
        editor.commit()
    }

    override fun isUserLogged(): String {
        val key = sharedPreferences.getString("user" , "")
        return key
    }

    override fun saveChannel(channel: String) {
        var idChannels = sharedPreferences.getString("channel", "")
        if (idChannels.isEmpty()){
            idChannels=channel
        }else {
            idChannels+=",$channel"
        }
        editor.putString("channel", idChannels)
        editor.commit()
    }

    override fun getIdChannels(): List<String> {
        return sharedPreferences.getString("channel" ,"").split(",")
    }

    override fun setFirstTimeLoaded() {
        editor.putString("first", "n")
        editor.commit()
    }

    override fun isFirstTime(): Boolean {
        val first = sharedPreferences.getString("first", "")
        if (first.isEmpty()){
            setFirstTimeLoaded()
            return true
        }else {
            return false
        }
    }

    override fun removeIdChannel(idChannel: String) {
        val list = getIdChannels().toMutableList()
        list.remove(idChannel)
        var idChannels = ""
        list.forEach {
            if (idChannels.isEmpty()){
                idChannels=it
            }else {
                idChannels+=",$it"
            }
        }
        editor.remove("channel")
        editor.apply()
        editor.putString("channel", idChannels)
        editor.commit()
    }

}