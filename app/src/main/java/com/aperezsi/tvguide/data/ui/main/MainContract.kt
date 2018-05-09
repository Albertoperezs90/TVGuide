package com.aperezsi.tvguide.data.ui.main

import android.content.Context
import android.media.tv.TvContract
import com.aperezsi.tvguide.data.ui.base.BaseFragment
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface MainContract {

    interface View : BaseView<Presenter> {
        fun getContext() : Context
        fun setNowListPrograms(nowPrograms: List<TvContract.Programs>)
    }

    interface Presenter : BasePresenter {
        fun loadNowProgramRepository()
    }
}