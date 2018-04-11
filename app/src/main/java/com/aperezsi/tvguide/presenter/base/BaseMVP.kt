package com.aperezsi.tvguide.presenter.base

import com.aperezsi.tvguide.views.base.BaseView

/**
 * Each presenter must implement this interface
 *
 * @param <V> View for presenter
 */
interface BaseMVP<V : BaseView> {

    /**
     * Called when view is attached to presenter
     *
     * @param view
     */
    fun attach(view: V)


    /**
     * Called when view is detached from presenter
     */
    fun detach()

    /**
     * @return true if view is attached to presenter
     */
    fun isAttached(): Boolean
}