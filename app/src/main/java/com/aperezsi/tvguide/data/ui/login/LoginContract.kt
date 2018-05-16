package com.aperezsi.tvguide.data.ui.login

import android.content.Context
import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

/**
 * Created by alberto on 16/05/2018.
 */
interface LoginContract {

    interface View : BaseView<Presenter>{
        fun getContext() : Context
    }

    interface Presenter : BasePresenter {

    }
}