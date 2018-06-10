package com.aperezsi.tvguide.data.service.interfaces

interface IStorage {

    fun saveUserKey(key: String)
    fun isUserLogged() : String
}