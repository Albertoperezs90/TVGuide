package com.aperezsi.tvguide.data.ui.main

import android.os.Bundle
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import com.aperezsi.tvguide.data.utils.BasePresenter

class MainActivity : BaseActivity(), MainContract.View {

    override fun getContentResource(): Int = R.layout.activity_main
    private lateinit var mainPresenter: MainPresenter

    init {
        mainPresenter = MainPresenter(null, this)
    }



}