package com.aperezsi.tvguide.data.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentResource())
        setSupportActionBar(getToolbar())
        setFragmentNavigation()
    }

    abstract fun getContentResource(): Int
    abstract fun getToolbar(): Toolbar
    abstract fun setFragmentNavigation()

}