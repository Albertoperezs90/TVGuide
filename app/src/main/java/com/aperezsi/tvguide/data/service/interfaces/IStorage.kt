package com.aperezsi.tvguide.data.service.interfaces

import com.aperezsi.tvguide.data.data.ProgramResponse

interface IStorage {

    fun saveUserKey(key: String)
    fun isUserLogged() : String
    fun saveChannel(channel: String)
    fun getIdChannels() : List<String>
    fun setFirstTimeLoaded()
    fun isFirstTime() : Boolean
}