package com.aperezsi.tvguide.data.ui.main.fragment.now

import android.os.Bundle
import android.view.View
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseFragment

/**
 * Created by alberto on 06/05/2018.
 */
class NowFragment : BaseFragment(), NowContract.View {

    private lateinit var presenter: NowContract.Presenter

    override fun getLayout(): Int {
        TODO("CREAR EL LAYOUT DEL FRAGMENTO, DA ERROR")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = NowPresenter()
    }
}