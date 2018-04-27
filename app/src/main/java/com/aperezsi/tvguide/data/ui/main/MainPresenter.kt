package com.aperezsi.tvguide.data.ui.main

import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.main.contract.MainContract

class MainPresenter constructor(programResponse: ProgramResponse, mainView: MainContract.View) : MainContract.Presenter {

    private val mainView: MainContract.View = mainView
    private val programResponse : ProgramResponse = programResponse

    override fun start() {
        loadCurrentPrograms()
    }

    override fun loadCurrentPrograms() {
        programResponse
    }
}