package com.aperezsi.tvguide.views.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.aperezsi.tvguide.views.base.BaseView

abstract class BaseActivity : AppCompatActivity(), BaseView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentResource())
    }


    /**
     * Layout resource to be inflated
     *
     * @return layout resource
     */

    @LayoutRes
    protected abstract fun getContentResource(): Int

    /**
     * Initialization
     */
    protected abstract fun init(state: Bundle?)
}
