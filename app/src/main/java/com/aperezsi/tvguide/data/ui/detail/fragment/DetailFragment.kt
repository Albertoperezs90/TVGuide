package com.aperezsi.tvguide.data.ui.detail.fragment

import com.aperezsi.tvguide.data.ui.base.BaseFragment

class DetailFragment : BaseFragment(), DetailFragmentContract.View {

    private val detailFragmentPresenter = DetailFragmentPresenter(this)

    override fun getLayout(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}