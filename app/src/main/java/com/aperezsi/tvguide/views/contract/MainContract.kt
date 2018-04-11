package com.aperezsi.tvguide.views.contract

import com.aperezsi.tvguide.presenter.base.BaseMVP
import com.aperezsi.tvguide.views.base.BaseView

interface MainContract {

    // User actions. Presenter wil implement
    interface Presenter: BaseMVP<View>{
        fun loadHelloText()
    }

    // Action callbacks. Activites wil implement
    interface View: BaseView {
        fun onTextLoaded(text: String)
    }
}