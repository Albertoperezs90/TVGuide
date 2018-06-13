package com.aperezsi.tvguide.data.service

import android.util.Log
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.interfaces.IFirebaseService
import com.aperezsi.tvguide.data.ui.main.MainPresenter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ChildEventListener



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
                       presenter.refreshUser(user)
                       presenter.alertDismiss()
                   }else {
                       logginUser(user)
                   }
               }
    }


    override fun getUserByEmail(user: User) {
        dbInstance.getReference("users").orderByChild("email").startAt(user.email).endAt(user.email).addChildEventListener(object : ChildEventListener {
            override fun onCancelled(dataSnapshot: DatabaseError) {
                Log.e("onCancelled", dataSnapshot.toString())
            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, p1: String?) {
                Log.e("onChildMoved", dataSnapshot.toString())
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, p1: String?) {
                Log.e("onChildChanged", dataSnapshot.toString())
            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {
                Log.e("onChildRemoved", dataSnapshot.toString())
            }

            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                dataSnapshot.children.forEach {
                    if (it.key == "id"){
                        presenter.saveUserToPreferences(it.value.toString())
                    }
                }
            }
        })
    }


    override fun logginUser(user: User) {
        authInstance.signInWithEmailAndPassword(user.email, user.password)
                .addOnCompleteListener(presenter.getActivity()) {  task ->
                    if (task.isSuccessful){
                        presenter.refreshUser(user)
                        getUserByEmail(user)
                        presenter.alertDismiss()
                    }else {
                        presenter.showToast("El usuario y/o contraseña son incorrectos")
                    }
                }
    }

    override fun updateUser(user: User) {
        dbInstance.reference.child("users").child(user.id).child("avatar").setValue(user.avatar)
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