package com.aperezsi.tvguide.data.service.interfaces

import com.aperezsi.tvguide.data.data.User
import com.google.firebase.auth.FirebaseUser

interface IFirebaseService {

    fun getCurrentUser() : FirebaseUser?
    fun logginUser(user: User)
    fun createUser(user: User)
    fun getUserFromDb(key: String)
    fun logoutUser()
    fun getUserByEmail(user: User)
    fun updateUser(user: User)
}