package com.aperezsi.tvguide.data.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import org.jetbrains.anko.toast
import com.aperezsi.tvguide.R.id.textView
import android.widget.Toast



class MainActivity : BaseActivity(), MainContract.View {

    private val mainPresenter: MainPresenter = MainPresenter(this)
    private lateinit var searchView: SearchView

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
                Toast.makeText(this@MainActivity, R.string.app_name, Toast.LENGTH_SHORT).show()
                //se oculta el EditText
                searchView.setQuery("", false)
                searchView.isIconified = true
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                toast(newText)
                return true
            }
        })
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_view, menu)
        searchView = menu!!.findItem(R.id.action_search).actionView as SearchView
        menu.findItem(R.id.action_search).collapseActionView()
        searchView.queryHint = getText(R.string.search_view_hint)
        initListeners()
        return super.onCreateOptionsMenu(menu)
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

}