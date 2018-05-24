package com.aperezsi.tvguide.data.ui.detail

import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

interface DetailContract {

    interface View : BaseView<Presenter>{
        fun setProgram()
    }

    interface Presenter : BasePresenter {
        fun setProgram(programResponse: ProgramResponse)
    }
}