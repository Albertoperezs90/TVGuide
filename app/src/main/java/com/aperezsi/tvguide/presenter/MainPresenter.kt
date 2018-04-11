package com.aperezsi.tvguide.presenter

import com.aperezsi.tvguide.presenter.base.BasePresenter
import com.aperezsi.tvguide.views.contract.MainContract
import java.util.*

class MainPresenter: BasePresenter<MainContract.View>(), MainContract.Presenter {

    private val helloTexts = arrayListOf("BONJOUR", "HOLA", "HALLO", "MERHABA", "HELLO", "CIAO", "KONNICHIWA")

    override fun loadHelloText() {
        val random = Random()
        val hello = helloTexts[random.nextInt(helloTexts.size)]
        getView().onTextLoaded(hello)
    }
}