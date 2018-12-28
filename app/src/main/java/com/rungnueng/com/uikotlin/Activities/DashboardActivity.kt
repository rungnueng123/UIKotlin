package com.rungnueng.com.uikotlin.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.rungnueng.com.uikotlin.Adapters.SlideImageAdapter
import com.rungnueng.com.uikotlin.Model.SlideImageModel
import com.rungnueng.com.uikotlin.R
import com.viewpagerindicator.CirclePageIndicator
import java.util.*



class DashboardActivity : AppCompatActivity() {

    private lateinit var mDrawerLayout: DrawerLayout

    private var imageModelArrayList: ArrayList<SlideImageModel>? = null
    private val myImageList = intArrayOf(
        R.drawable.harley2,
        R.drawable.benz2,
        R.drawable.vecto,
        R.drawable.webshots,
        R.drawable.bikess,
        R.drawable.img1
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        mDrawerLayout = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(
            this, mDrawerLayout, toolbar, R.string.search, R.string.search
        )
        mDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener { menuItem ->
            // set item as selected to persist highlight
            menuItem.isChecked = true
            // close drawer when item is tapped
            mDrawerLayout.closeDrawers()

            // Add code here to update the UI based on the item selected
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this,DashboardActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_shop -> {
                    val intent = Intent(this,ShopActivity::class.java)
                    startActivity(intent)
                }
                R.id.nav_cart -> {
                    Toast.makeText(this, "cart", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.nav_about -> {
                    Toast.makeText(this, "about", Toast.LENGTH_LONG).show()
                    true
                }
                R.id.nav_feedback -> {
                    Toast.makeText(this, "feedback", Toast.LENGTH_LONG).show()
                    true
                }
            }

            true
        }

        imageModelArrayList = ArrayList()
        imageModelArrayList = populateList()

        initImageSlide()

    }

    private fun populateList(): ArrayList<SlideImageModel>? {
        val list = ArrayList<SlideImageModel>()

        for (i in 0..5) {
            val slideImageModel = SlideImageModel()
            slideImageModel.setImage_drawables(myImageList[i])
            list.add(slideImageModel)
        }

        return list
    }

    private fun initImageSlide() {
        mPager = findViewById(R.id.pager) as ViewPager
        mPager!!.adapter = SlideImageAdapter(this, this.imageModelArrayList!!)

        val indicator: CirclePageIndicator = findViewById(R.id.indicator)

        indicator.setViewPager(mPager)

        val density = resources.displayMetrics.density

        indicator.setRadius(5 * density)
        NUM_PAGES = imageModelArrayList!!.size

        val handler = Handler()
        val Update = Runnable {
            if (currentPage == NUM_PAGES) {
                currentPage = 0
            }
            mPager!!.setCurrentItem(currentPage++, true)
        }
        val swipeTimer = Timer()
        swipeTimer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(Update)
            }
        }, 3000, 3000)

        // Pager listener over indicator
        indicator.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageSelected(position: Int) {
                currentPage = position

            }

            override fun onPageScrolled(pos: Int, arg1: Float, arg2: Int) {

            }

            override fun onPageScrollStateChanged(pos: Int) {

            }
        })
    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle presses on the action bar menu items
        when (item.itemId) {
            R.id.action_cart -> {
                Toast.makeText(this, "a", Toast.LENGTH_LONG).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        private var mPager: ViewPager? = null
        private var currentPage = 0
        private var NUM_PAGES = 0
    }
}
