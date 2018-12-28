package com.rungnueng.com.uikotlin.Activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rungnueng.com.uikotlin.Fragments.ShopFragment
import com.rungnueng.com.uikotlin.R

class ShopActivity : AppCompatActivity() {

    private lateinit var shopFragment: ShopFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        initInstance(savedInstanceState)

    }

    private fun initInstance(savedInstanceState: Bundle?) {

        //Add Fragment
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container, ShopFragment.newInstance(), "rageComicList")
                .commit()
        }
    }

}
