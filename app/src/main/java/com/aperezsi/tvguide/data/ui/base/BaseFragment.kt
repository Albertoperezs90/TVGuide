package com.aperezsi.tvguide.data.ui.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aperezsi.tvguide.data.data.ProgramResponse
import com.aperezsi.tvguide.data.utils.helpers.FragmentNavigation

/**
 * Created by alberto on 06/05/2018.
 */
abstract class BaseFragment : Fragment(), FragmentNavigation.View {

    protected lateinit var rootView: View
    protected lateinit var navigationPresenter: FragmentNavigation.Presenter

    abstract fun getLayout() : Int
    abstract fun refreshAdapter(programs: List<ProgramResponse>)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(getLayout(), container, false)
        return rootView
    }



}