package com.fs.dcc.finalapp

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.fs.dcc.finalapp.adapters.PagerAdapter
import com.fs.dcc.finalapp.fragments.ChatFragment
import com.fs.dcc.finalapp.fragments.InfoFragment
import com.fs.dcc.finalapp.fragments.RatesFragment
import com.fs.dcc.mylibrary.ToolbarActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : ToolbarActivity() {

    private var prevBottomSelected: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbarToLoad(toolbarView as Toolbar)

        setUpViewPager(getPagerAdapter())
        setUpBottomNavigationBar()
    }

    private fun getPagerAdapter(): PagerAdapter {
        val adapter = PagerAdapter(supportFragmentManager)
        adapter.addFragment(InfoFragment())
        adapter.addFragment(ChatFragment())
        adapter.addFragment(RatesFragment())
        return adapter
    }

    private fun setUpViewPager(adapter: PagerAdapter) {

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {

                if (prevBottomSelected == null) {
                    bottomNavView.menu.getItem(0).isChecked = false
                } else {
                    prevBottomSelected!!.isChecked = false
                }
                bottomNavView.menu.getItem(position).isChecked = true
                prevBottomSelected = bottomNavView.menu.getItem(position)

            }

        })

    }

    private fun setUpBottomNavigationBar() {

        bottomNavView.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {

                R.id.bottom_nav_info -> {
                    viewPager.currentItem = 0; true
                }

                R.id.bottom_nav_chat -> {
                    viewPager.currentItem = 1; true
                }

                R.id.bottom_nav_rates -> {
                    viewPager.currentItem = 2; true
                }

                else -> false
            }

        }

    }

}
