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
}