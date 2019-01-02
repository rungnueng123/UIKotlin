package com.rungnueng.com.uikotlin.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rungnueng.com.uikotlin.Fragments.CartFragment
import com.rungnueng.com.uikotlin.R

class CartActivity : AppCompatActivity() {

    private lateinit var cartFragment: CartFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        initInstance(savedInstanceState)

    }

    private fun initInstance(savedInstanceState: Bundle?) {
        //Add Fragment
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, CartFragment.newInstance(), "rageComicList")
                .commit()
        }
    }
}
