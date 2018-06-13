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
        val currentUser = authInstance.currentUser
        return currentUser
    }

    override fun logoutUser() {
        authInstance.signOut()
    }

    override fun createUser(user: User) {
        authInstance.createUserWithEmailAndPassword(user.email, user.password)
               .addOnCompleteListener(presenter.getActivity()) { task ->
                   if (task.isSuccessful){
                       val refUser = dbInstance.getReference("users")
                       val userId = refUser.push().key
                       user.id = userId!!
                       refUser.child(userId!!).setValue(user)
                       presenter.saveUserToPreferences(userId)
                       presenter.refreshUser()
                       presenter.alertDismiss()
                   }else {
                       logginUser(user)
                   }
               }
    }

    override fun logginUser(user: User) {
        authInstance.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener(presenter.getActivity()) {  task ->
                    if (task.isSuccessful){
                        presenter.refreshUser()
                        presenter.alertDismiss()
                    }else {
                        presenter.showToast("El usuario y/o contraseña son incorrectos")
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