package com.aperezsi.tvguide.data.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast
import android.widget.Toast
import com.aperezsi.tvguide.R.id.search_src_text
import com.aperezsi.tvguide.data.ui.main.fragment.now.NowFragment
import com.aperezsi.tvguide.R.id.drawerLayout
import android.os.Bundle
import com.aperezsi.tvguide.data.service.AuthValidator
import com.aperezsi.tvguide.data.service.FirebaseService
import kotlinx.android.synthetic.main.nav_drawer_header.*


class MainActivity : BaseActivity(), MainContract.View {

    private val mainPresenter: MainPresenter = MainPresenter(this)
    private lateinit var searchView: SearchView
    private lateinit var menuItem: MenuItem

    override fun getContentResource(): Int = R.layout.activity_main
    override fun getContext(): Context = this
    override fun setFragmentNavigation() = mainPresenter.setNavigation(supportFragmentManager, tabs, viewpager)

    override fun onStart() {
        super.onStart()
        setSupportActionBar(toolbar)
        navigation_view.setNavigationItemSelectedListener(this)
        attachDrawerLayout()
    }


    override fun initListeners() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.setQuery("", false)
                menuItem.collapseActionView()
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                mainPresenter.filterSuggestions(newText)
                return true
            }
        })

        drawer_header_iv.setOnClickListener {
            FirebaseService.pushUser()
            AuthValidator.getToken()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)
        menuItem = menu!!.findItem(R.id.action_search)
        searchView = menuItem.actionView as SearchView
        customizeSearchView()
        initListeners()
        return super.onCreateOptionsMenu(menu)
    }


    override fun customizeSearchView() {
        val autoComplete = searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text) as SearchView.SearchAutoComplete
        val searchplate = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate) as View
        val searchCloseIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_close_btn) as ImageView
        val voiceIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_voice_btn) as ImageView
        val searchIcon = searchView.findViewById(android.support.v7.appcompat.R.id.search_mag_icon) as ImageView

        searchView.queryHint = getString(R.string.search_view_hint)
        autoComplete.setHintTextColor(Color.LTGRAY)
        autoComplete.setTextColor(Color.WHITE)
    }


    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }
    }

    override fun setPrograms() {
        mainPresenter.setProgramsList(intent.getSerializableExtra("programs") as APIResponse)
    }

    override fun attachDrawerLayout() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val mDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_login, R.string.drawer_login)
        mDrawerToggle.setDrawerIndicatorEnabled(true)
        drawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.nav_item_one -> toast("clicked one")
            R.id.nav_item_two -> toast("clicked two")
            R.id.nav_item_three -> toast("clicked three")
            R.id.nav_item_four -> toast("clicked four")
            R.id.nav_item_five -> toast("clicked five")
            R.id.nav_item_six -> toast("clicked six")
            R.id.nav_item_seven -> toast("clicked seven")
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun refreshAdapter() {

    }


}