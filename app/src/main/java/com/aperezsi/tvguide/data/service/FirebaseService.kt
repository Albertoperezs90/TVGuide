package com.aperezsi.tvguide.data.service

import android.util.Log
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.interfaces.IFirebaseService
import com.aperezsi.tvguide.data.ui.main.MainPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseService (val presenter: MainPresenter) : IFirebaseService {


    val authInstance = FirebaseAuth.getInstance()
    val dbInstance = FirebaseDatabase.getInstance()

    override fun getCurrentUser(): FirebaseUser? {
        val currentUser = FirebaseAuth.getInstance().currentUser
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
                       presenter.alertDismiss()
                   }else {
                       presenter.showToast("Ya existe un usuario con el email ${user.email}")
                   }
               }
    }

    override fun logginUser(user: User) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener(presenter.getActivity()) {  task ->
                    if (task.isSuccessful){
                        presenter.refreshUser()
                    }else {
                        Log.e("logginUser", task.exception.toString())
                    }
                }
    }

    override fun getUserFromDb(key: String) {
        val listener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue(User::class.java)
                presenter.logginUser(user!!)
            }

            override fun onCancelled(dataError: DatabaseError) {
                Log.e("ERROR", dataError.message)
            }
        }
        val refUsers = dbInstance.getReference("users").child(key).addValueEventListener(listener)
    }




}