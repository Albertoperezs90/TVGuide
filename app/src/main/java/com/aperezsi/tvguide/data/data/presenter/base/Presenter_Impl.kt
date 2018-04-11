package com.aperezsi.tvguide.data.data.presenter.base

import com.aperezsi.tvguide.data.data.model.Program

interface Presenter_Impl {

    fun onResume()

    fun onDestroy()

    fun saveProgram(program: Program)
}