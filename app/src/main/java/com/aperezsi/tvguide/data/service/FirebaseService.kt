package com.aperezsi.tvguide.data.service

import android.util.Log
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.interfaces.IFirebaseService
import com.aperezsi.tvguide.data.ui.main.MainPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class FirebaseService (val presenter: MainPresenter) : IFirebaseService {


    val authInstance = FirebaseAuth.getInstance()
    val dbInstance = FirebaseDatabase.getInstance()

    override fun getCurrentUser(): FirebaseUser? {
        val currentUser = authInstance.currentUser
        return currentUser
    }

    override fun createUser(user: User) {
        authInstance.createUserWithEmailAndPassword(user.email, user.password)
               .addOnCompleteListener(presenter.getActivity()) { task ->
                   if (task.isSuccessful){
                       val firebaseUser = authInstance.currentUser
                       user.id = firebaseUser!!.uid
                       val refUser = dbInstance.getReference("users")
                       val userId = refUser.push().key
                       refUser.child(userId!!).setValue(user)
                       presenter.saveUserToPreferences(userId)
                   }else {
                       Log.e("createUser", task.exception.toString())
                   }
               }
    }

    override fun logginUser(user: User) {
        authInstance.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener(presenter.getActivity()) {  task ->
                    if (task.isSuccessful){
                        presenter.refreshUser()
                    }else {
                        Log.e("logginUser", task.exception.toString())
                    }
                }
    }

    override fun getUserFromDb(key: String): User {
        val refUsers = dbInstance.getReference("users")
        val user = refUsers.child(key) as User
        return user
    }

}