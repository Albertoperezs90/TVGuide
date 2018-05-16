package com.aperezsi.tvguide.data.ui.login

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.widget.FrameLayout
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.ui.login.fragment.LoginFragment
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation
import kotlinx.android.synthetic.main.activity_login.view.*

/**
 * Created by alberto on 16/05/2018.
 */
class LoginPresenter (val loginView: LoginContract.View) : LoginContract.Presenter, FragmentNavigation.Presenter {

    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        val fragment = LoginFragment()
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment).commit()
    }


}