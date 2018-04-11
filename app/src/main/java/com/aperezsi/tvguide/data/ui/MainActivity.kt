package com.aperezsi.tvguide.data.ui

import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.presenter.MainPresenter
import com.aperezsi.tvguide.data.ui.base.BaseActivity

class MainActivity: BaseActivity() {

    override fun getContentResource(): Int = R.layout.activity_main

    private var presenter: MainPresenter? = null

    init {
        presenter = MainPresenter(this)
    }
}