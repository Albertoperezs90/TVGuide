package com.aperezsi.tvguide.data.ui.initial.fragment

import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.ui.main.data.ProgramRepository
import com.aperezsi.tvguide.data.utils.enums.TimerEnum
import com.aperezsi.tvguide.data.utils.helpers.TimeHelper

/**
 * Created by alberto on 17/05/2018.
 */
class InitialFragmentPresenter (val initialView: InitialFragment) : InitialFragmentContract.Presenter {

    override fun startAPICalls() {
        val programRepository = ProgramRepository(this)
        programRepository.getPrimetime(TimeHelper.getCurrentEpoch().toString())
    }

    override fun loadAPIResponseList(apiResponse: APIResponse) {
        initialView.startActivityWithResource(apiResponse)
    }
}
