package com.aperezsi.tvguide.data.ui.initial.fragment

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.Typeface
import android.os.Build
import android.os.Handler
import android.support.annotation.RequiresApi
import android.transition.TransitionManager
import android.view.View
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.initial.InitialActivity
import com.aperezsi.tvguide.data.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_initial.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.concurrent.schedule

/**
 * Created by alberto on 17/05/2018.
 */
class InitialFragment : BaseFragment(), InitialFragmentContract.View {

    val initialFragmentPresenter = InitialFragmentPresenter(this)

    override fun getLayout(): Int = R.layout.fragment_initial


    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onStart() {
        super.onStart()
        initialFragmentPresenter.startAPICalls()
        startAnimation()
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun startAnimation() {
        text.typeface = Typeface.create("sans-serif-condensed", Typeface.BOLD)
        val handler = Handler()
        handler.postDelayed(Runnable() {
            TransitionManager.beginDelayedTransition(delayed_container)
            text.visibility = if (text.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }, 1500)
    }

    override fun startActivityWithResource(apiResponse: APIResponse) {
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra("programs", apiResponse)
        startActivity(intent)
    }

    override fun refreshAdapter(programs: List<ProgramResponse>) {

    }
}