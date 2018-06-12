package com.aperezsi.tvguide.data.ui.main

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.SearchView
import android.util.Log
import android.util.Patterns
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.aperezsi.tvguide.R
import com.aperezsi.tvguide.data.data.APIResponse
import com.aperezsi.tvguide.data.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import com.aperezsi.tvguide.data.data.User
import com.aperezsi.tvguide.data.service.AuthValidator
import com.aperezsi.tvguide.data.service.FirebaseService
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.dialog_login.view.*
import kotlinx.android.synthetic.main.nav_drawer_header.*
import org.jetbrains.anko.alert
import kotlin.math.log


class MainActivity : BaseActivity(), MainContract.View {

    private val mainPresenter: MainPresenter = MainPresenter(this)
    private lateinit var searchView: SearchView
    private lateinit var menuItem: MenuItem
    private var user: User? = null
    private lateinit var alertDialog: AlertDialog
    private val PICK_IMAGE = 1

    override fun getContentResource(): Int = R.layout.activity_main
    override fun getContext(): Context = this
    override fun setFragmentNavigation() = mainPresenter.setNavigation(supportFragmentManager, tabs, viewpager)
    override fun getActivity(): MainActivity = this

    override fun onStart() {
        super.onStart()
        setSupportActionBar(toolbar)
        navigation_view.setNavigationItemSelectedListener(this)
        attachDrawerLayout()
        mainPresenter.checkIfUserIsLogged()
        if (mainPresenter.isFirsTime()){
            mainPresenter.loadFavourites()
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

        drawer_header.setOnClickListener {
            if (user != null){
                //TODO VENTANA MODIFICAR FOTOS, ETC...
            }else {
                buildDialog()
            }
        }

        drawer_header_iv.setOnClickListener {
            if (user != null){
                pickImage()
            }
        }
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
            R.id.all_programs -> mainPresenter.filterPrograms("")
            R.id.favourites_programs -> mainPresenter.filterPrograms("fav")
            R.id.movies_programs -> mainPresenter.filterPrograms("movie")
            R.id.series_programs -> mainPresenter.filterPrograms("serie")
            R.id.news_programs -> mainPresenter.filterPrograms("newspaper")
            R.id.graduate_programs -> mainPresenter.filterPrograms("graduate")
            R.id.sport_programs -> mainPresenter.filterPrograms("deportes")
            R.id.documental_programs -> mainPresenter.filterPrograms("documental")
            R.id.footbal_programs -> mainPresenter.filterPrograms("futbol")
            R.id.music_programs -> mainPresenter.filterPrograms("musica")
            R.id.joke_programs -> mainPresenter.filterPrograms("humor")
            R.id.cook_programs -> mainPresenter.filterPrograms("cook")
        }

        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }


    override fun refreshUser(user: FirebaseUser?) {
        if (user != null){
            val name = user.email!!.substring(0, user.email!!.indexOf('@'))
            this.user = User(user.uid, name, user.email!!, "", "")
            drawer_header_tv_name.text = name
        }
    }


    override fun updateUI(data: Intent) {
        user = data.getSerializableExtra("user") as User
        mainPresenter.createUser(user!!)
        drawer_header_tv_name.text = user!!.nickname
        toast("Bienvenido ${user!!.nickname}")
    }


    override fun buildDialog() {
        val inflater = layoutInflater
        val loginLayout = inflater.inflate(R.layout.dialog_login, null)
        alertDialog = AlertDialog.Builder(this)
            .setView(loginLayout)
            .setPositiveButton(R.string.accept, null)
            .setNegativeButton(R.string.cancel,null)
            .create()

        alertDialog.setOnShowListener { dialogInterface ->
            val button = (dialogInterface as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
            button.setOnClickListener { view ->
                val email = loginLayout.email.text.toString()
                val password = loginLayout.pass.text.toString()
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if (!password.isNullOrEmpty()){
                        val user = User("", email.substring(0, email.indexOf('@')),email, password)
                        mainPresenter.createUser(user)
                    }else {
                        toast("El campo contraseña es obligatorio")
                    }
                }else {
                    toast("Debe escribir un e-mail valido")
                }
            }
        }
        alertDialog.show()
    }


    override fun alertDismiss() {
        alertDialog.dismiss()
    }

    override fun showToast(message: String) {
        toast(message)
    }

    override fun pickImage() {
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Selecciona foto"), PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == PICK_IMAGE){
            if (data != null){
                val inputStream = this.getContentResolver().openInputStream(data.data)
            }
        }
    }

}