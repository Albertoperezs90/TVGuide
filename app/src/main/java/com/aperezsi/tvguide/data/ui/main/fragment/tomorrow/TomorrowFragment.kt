package com.aperezsi.tvguide.data.ui.main.fragment.tomorrow

import android.os.Bundle
import android.view.View
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseFragment

/**
 * Created by alberto on 06/05/2018.
 */
class TomorrowFragment : BaseFragment(), TomorrowContract.View {

    private lateinit var tomorrowPresenter: TomorrowPresenter

    override fun getLayout(): Int = R.layout.fragment_tomorrow

    override fun onStart() {
        super.onStart()
        tomorrowPresenter = TomorrowPresenter()
    }
}