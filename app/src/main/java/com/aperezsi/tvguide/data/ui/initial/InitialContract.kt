package com.aperezsi.tvguide.data.ui.initial

import com.aperezsi.tvguide.data.ui.base.BasePresenter
import com.aperezsi.tvguide.data.ui.base.BaseView

/**
 * Created by alberto on 17/05/2018.
 */
interface InitialContract {

    public interface View : BaseView<Presenter>{

    }

    public interface Presenter : BasePresenter {

    }
}