package com.aperezsi.tvguide.presenter.interfaces

import com.aperezsi.tvguide.views.interfaces.BaseView

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