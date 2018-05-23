package com.aperezsi.tvguide.data.ui.detail

import com.aperezsi.tvguide.data.ui.base.BaseActivity

class DetailActivity : BaseActivity(), DetailContract.View {

    private val detailPresenter = DetailPresenter(this)

    override fun getContentResource(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setFragmentNavigation() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}