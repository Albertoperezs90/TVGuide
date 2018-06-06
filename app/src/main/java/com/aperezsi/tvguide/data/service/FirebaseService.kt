package com.aperezsi.tvguide.data.service

import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.interfaces.IFirebaseService
import com.google.firebase.database.FirebaseDatabase

object FirebaseService : IFirebaseService {


    override fun pushUser() {
        val db = FirebaseDatabase.getInstance()
        db.getReference("users").setValue("Alberto")
    }

    override fun loginUser(user: User) {

    }
}