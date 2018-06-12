package com.aperezsi.tvguide.data.ui.login

import android.app.ActionBar
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.Toolbar
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by alberto on 16/05/2018.
 */
class LoginActivity : BaseActivity(), LoginContract.View {

    private val loginPresenter = LoginPresenter(this)


    override fun getContentResource(): Int = R.layout.activity_login
    override fun getContext(): Context = this
    override fun setFragmentNavigation() = loginPresenter.setNavigation(supportFragmentManager)

    override fun onBackPressed() {
        val user = User("", "alberto", "alberto@gmail.com", "lollol")
        val returnIntent = Intent()
        returnIntent.putExtra("user", user)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}