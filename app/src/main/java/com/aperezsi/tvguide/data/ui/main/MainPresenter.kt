package com.aperezsi.tvguide.data.ui.main

import com.aperezsi.tvguide.data.data.ProgramResponse

class MainPresenter constructor(programResponse: ProgramResponse?, mainView: MainContract.View) : MainContract.Presenter {

    private val mainView: MainContract.View = mainView
    private val programResponse : ProgramResponse? = null


    override fun loadCurrentPrograms() {

    }
}