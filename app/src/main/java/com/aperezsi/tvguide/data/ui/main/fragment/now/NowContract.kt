package com.aperezsi.tvguide.data.ui.main.fragment.now

import android.content.Context
import android.support.v4.app.FragmentActivity
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView
import com.aperezsi.tvguide.data.ui.main.data.NowAdapter

/**
 * Created by alberto on 06/05/2018.
 */
interface NowContract {

    interface View : BaseView<Presenter> {
        fun getFragmentActivity() : FragmentActivity
        fun getFragmentContext() : Context
        fun attachAdapter(adapter: NowAdapter)
        fun notifyDataAdapterChanged()
    }

    interface Presenter : BasePresenter {
        fun loadNowPrograms()
        fun buildAdapter(layout: Int)
    }
}