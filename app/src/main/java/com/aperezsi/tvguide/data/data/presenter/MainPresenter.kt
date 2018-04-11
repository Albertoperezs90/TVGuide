package com.aperezsi.tvguide.data.data.presenter

import com.aperezsi.tvguide.data.data.model.Program
import com.aperezsi.tvguide.data.data.presenter.base.Presenter_Impl
import com.aperezsi.tvguide.data.ui.base.BaseActivity

class MainPresenter(view: BaseActivity): Presenter_Impl {

    var view: BaseActivity? = null

    init {
        this.view = view
    }

    override fun onResume() {

    }

    override fun onDestroy() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveProgram(program: Program) {

    }
}