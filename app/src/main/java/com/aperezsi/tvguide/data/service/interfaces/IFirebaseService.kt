package com.aperezsi.tvguide.data.service.interfaces

import com.aperezsi.tvguide.data.data.User

interface IFirebaseService {

    fun loginUser(user: User)

    fun pushUser()
}