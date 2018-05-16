package com.aperezsi.tvguide.data.ui.login

import android.app.ActionBar
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.aperezsi.tvguide.R
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
    override fun getToolbar(): Toolbar? = null
    override fun getContext(): Context = this
    override fun setFragmentNavigation() = loginPresenter.setNavigation(supportFragmentManager)

}