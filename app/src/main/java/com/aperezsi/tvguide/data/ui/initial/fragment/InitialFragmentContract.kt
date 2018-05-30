package com.aperezsi.tvguide.data.ui.initial.fragment

import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

/**
 * Created by alberto on 17/05/2018.
 */
interface InitialFragmentContract {

    public interface View : BaseView<Presenter>{
        fun startAnimation()
        fun startActivityWithResource(apiResponse: APIResponse)
    }

    public interface Presenter : BasePresenter {
        fun startAPICalls()
        fun loadAPIResponseList(apiResponse: APIResponse)
    }
}