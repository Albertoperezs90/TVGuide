package com.aperezsi.tvguide.data.ui.main

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.support.design.widget.NavigationView
import android.view.ViewManager
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView
import com.google.firebase.auth.FirebaseUser
import org.jetbrains.anko.AlertBuilder

interface MainContract {

    interface View : BaseView<Presenter>, NavigationView.OnNavigationItemSelectedListener {
        fun getContext() : Context
        fun getActivity() : MainActivity
        fun setPrograms()
        fun attachDrawerLayout()
        fun customizeSearchView()
        fun initListeners()
        fun refreshUser(user: User?)
        fun updateUI(data: Intent)
        fun buildDialog()
        fun alertDismiss()
        fun showToast(message: String)
        fun pickImage()
    }

    interface Presenter : BasePresenter {
        fun setProgramsList(nowPrograms: APIResponse)
        fun filterSuggestions(newQuery: String) : List<ProgramResponse>
        fun getActivity() : MainActivity
        fun saveUserToPreferences(key: String)
        fun checkIfUserIsLogged()
        fun createUser(user: User)
        fun showToast(message: String)
        fun alertDismiss()
        fun refreshUser(user: User?)
        fun logginUser(user: User)
        fun loadFavourites()
        fun isFirsTime() : Boolean
        fun filterPrograms(filter: String)
        fun getFavouritePrograms() : MutableList<ProgramResponse>
        fun logoutUser()
        fun encodeBitmap(bitmap: Bitmap) : String
        fun decodeBitmap(user : User) : Bitmap
        fun updateUserData(user: User)
    }
}