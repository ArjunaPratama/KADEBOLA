package com.arjuna.kadebola

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.arjuna.kadebola.Fragment.NextFragment
import com.arjuna.kadebola.Fragment.PrevFragment
import com.arjuna.kadebola.R.id.navigation_home
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//
//            }
//            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadMatchLastFragment(savedInstanceState)
                    val fragment = NextFragment.newInstance()
                }
                R.id.navigation_dashboard -> {
                    loadMatchNextFragment(savedInstanceState)
                }
            }
            true
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = navigation_home
    }

    private fun loadMatchLastFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, PrevFragment(), PrevFragment::class.simpleName)
                    .commit()
        }

    }

    private fun loadMatchNextFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, NextFragment(), NextFragment::class.simpleName)
                    .setCustomAnimations(R.anim.design_bottom_sheet_slide_in, R.anim.design_bottom_sheet_slide_out)
                    .commit()

        }

    }
}
