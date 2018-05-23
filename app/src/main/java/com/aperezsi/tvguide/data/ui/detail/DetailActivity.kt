package com.aperezsi.tvguide.data.ui.detail

import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import com.google.gson.Gson

class DetailActivity : BaseActivity(), DetailContract.View {

    private val detailPresenter = DetailPresenter(this)

    override fun getContentResource(): Int = R.layout.activity_detail
    override fun setFragmentNavigation() = detailPresenter.setNavigation(supportFragmentManager)

    override fun setProgram() {
        detailPresenter.setProgram(Gson().fromJson(intent.getStringExtra("program"), ProgramResponse::class.java))
    }
}