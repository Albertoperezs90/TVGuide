package com.aperezsi.tvguide.data.ui.initial

import android.support.v7.widget.Toolbar
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.base.BaseActivity

/**
 * Created by alberto on 17/05/2018.
 */
class InitialActivity : BaseActivity(), InitialContract.View {

    private val initialPresenter = InitialPresenter()

    override fun getContentResource(): Int = R.layout.activity_initial
    override fun getToolbar(): Toolbar? = null
    override fun setFragmentNavigation() = initialPresenter.setNavigation(supportFragmentManager)
}