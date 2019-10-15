package com.example.fitmate

import android.location.LocationListener
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import com.android.volley.RequestQueue

class MainActivity : AppCompatActivity(){

    var requestQueue: RequestQueue? = null
    var coordinates:String? = "28.605280;77.105402"
    var url:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val w = window
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun getLocation(){
        var locationListener: LocationListener? = null
    }

    fun getData(){

    }
}