package com.aperezsi.tvguide.data.ui.main.fragment.now

import android.app.Activity
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.ui.main.data.NowAdapter

/**
 * Created by alberto on 06/05/2018.
 */
class NowPresenter (val nowView: NowContract.View) : NowContract.Presenter {

    var nowPrograms: APIResponse? = null

    override fun loadNowPrograms() {
        nowPrograms = (nowView.getFragmentActivity() as Activity).intent.getSerializableExtra("programs") as APIResponse
    }

    override fun buildAdapter(layout: Int) {
        loadNowPrograms()
        val adapter = NowAdapter(nowView.getFragmentContext(), layout, nowPrograms!!)
        nowView.attachAdapter(adapter)
        nowView.notifyDataAdapterChanged()
    }

}