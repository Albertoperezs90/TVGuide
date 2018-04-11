package com.aperezsi.tvguide.presenter

import com.aperezsi.tvguide.presenter.interfaces.BaseMVP
import com.aperezsi.tvguide.views.interfaces.BaseView

class BasePresenter<V : BaseView> : BaseMVP<V> {

    /**
     * Attached view
     */
    private var mView: V? = null

    /**
     * @return The View attached to presenter
     */
    fun getView() : V = mView!!

    override fun attach(view: V) {
        mView = view
    }

    override fun detach() {
        mView = null
    }

    override fun isAttached(): Boolean = mView != null
}