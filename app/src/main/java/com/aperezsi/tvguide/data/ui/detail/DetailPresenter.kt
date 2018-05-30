package com.aperezsi.tvguide.data.ui.detail

import android.support.design.widget.TabLayout
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.ui.detail.fragment.DetailFragment
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation

class DetailPresenter (val detailView: DetailContract.View) : DetailContract.Presenter, FragmentNavigation.Presenter {

    private var programResponse: ProgramResponse? = null

    override fun setProgram(programResponse: ProgramResponse) {
        this.programResponse = programResponse
    }

    override fun setNavigation(fragmentManager: FragmentManager, tabLayout: TabLayout?, viewPager: ViewPager?) {
        detailView.setProgram()
        val fragment = DetailFragment(programResponse!!)
        fragmentManager.beginTransaction().add(R.id.detailContainer, fragment).commit()
    }
}