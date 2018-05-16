package com.aperezsi.tvguide.data.ui.login.fragment

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by alberto on 16/05/2018.
 */
class LoginFragment : BaseFragment(), LoginFragmentContract.View {

    override fun getLayout(): Int = R.layout.fragment_login

    override fun onStart() {
        super.onStart()
        startVideoView()
        initGoogleSignIn()
    }

    override fun startVideoView() {
        val uri = Uri.parse("android.resource://${activity!!.packageName}/${R.raw.video_background}")
        videoView.setVideoURI(uri)
        videoView.requestFocus()
        videoView.start()

        videoView.setOnPreparedListener { mediaPlayer ->
            mediaPlayer.isLooping = true
        }
    }

    override fun initGoogleSignIn() {
        val button: TextView = share_button.getChildAt(0) as TextView
        button.text = getString(R.string.google_signin)

        val gso : GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        val googleClient = GoogleSignIn.getClient(activity!!,gso)

        share_button.setOnClickListener {
            val intent = googleClient.signInIntent
            startActivityForResult(intent, 1)
        }
    }
}