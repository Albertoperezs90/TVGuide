package com.aperezsi.tvguide.views

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.presenter.MainPresenter
import com.aperezsi.tvguide.views.base.BaseActivity
import com.aperezsi.tvguide.views.contract.MainContract
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: BaseActivity(), MainContract.View {

    private lateinit var mTextView: TextView
    private lateinit var mPresenter: MainPresenter

    override fun getContentResource(): Int = R.layout.activity_main

    override fun init(state: Bundle?) {
        mTextView = tvHello
        mTextView.setOnClickListener{
            mPresenter.loadHelloText()
        }
        mPresenter = MainPresenter()
        mPresenter.attach(this)
        mPresenter.loadHelloText()
    }

    override fun onTextLoaded(text: String) {
        mTextView.text = text
    }

    
}
