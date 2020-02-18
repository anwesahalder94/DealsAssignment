package com.example.dealsassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.example.dealsassignment.R
import com.example.dealsassignment.view.adapter.ViewPagerAdapter
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class DashboardActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val pageTitle = arrayOf("Top", "Popular", "Featured")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setNavigationDrawer()
        setViewPager()
        setEventListener()
    }

    //setting up the items present in navigation drawer
    private fun setNavigationDrawer() {
        //create default navigation drawer toggle
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        toggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        //setting Tab layout (number of Tabs = number of ViewPager pages)
        for (i in 0..2) {
            tab_layout.addTab(tab_layout.newTab().setText(pageTitle[i]))
        }

        //set gravity for tab bar
        tab_layout.tabGravity = TabLayout.GRAVITY_FILL

        //handling navigation view item event
        nav_view.setNavigationItemSelectedListener(this)
    }

    //setting up the view pager
    private fun setViewPager() {
        //set viewpager adapter
        val pagerAdapter =
            ViewPagerAdapter(supportFragmentManager)
        view_pager.adapter = pagerAdapter

        //change Tab selection when swipe ViewPager
        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab_layout))

        //change ViewPager page when tab selected
        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                view_pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    /**
     * setEventListeners function
     * used to set the event listener for elements
     */
    private fun setEventListener() {

        floating_action_button.setOnClickListener{
            Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
        }
    }

    //handling the selected listener of items in navigation drawer
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val id = item.itemId

        when (id) {
            R.id.nav_menu_top -> view_pager!!.currentItem = 0
            R.id.nav_menu_popular -> view_pager?.currentItem = 1
            R.id.nav_menu_featured -> view_pager?.currentItem = 2
            R.id.nav_menu_settings -> Toast.makeText(this, "Settings yet implemented", Toast.LENGTH_SHORT).show()
            R.id.nav_menu_home -> Toast.makeText(this, "Home yet implemented", Toast.LENGTH_SHORT).show()
            R.id.nav_menu_log_out -> Toast.makeText(this, "Logout yet implemented", Toast.LENGTH_SHORT).show()
        }
        drawer_layout?.closeDrawer(GravityCompat.START)

        return true
    }

    //on back pressing the mobile back key
    override fun onBackPressed() {
        assert(drawer_layout != null)
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}