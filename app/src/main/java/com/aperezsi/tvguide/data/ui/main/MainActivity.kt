package com.aperezsi.tvguide.data.ui.main

import android.os.Bundle
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getContentResource(): Int = R.layout.activity_main

    private val mainPresenter: MainPresenter

    init {
        mainPresenter = MainPresenter()
    }
}