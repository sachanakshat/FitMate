package com.example.fitmate

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

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
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var locationListener = object : LocationListener{
            override fun onLocationChanged(location: Location?) {
                if(location != null) {
                    coordinates = "${location.longitude};${location.latitude}"
                }
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

            override fun onProviderEnabled(p0: String?) {}

            override fun onProviderDisabled(p0: String?) {}
        }
    }

    fun getData(){
        var aqiReceiver = JsonObjectRequest(this,url,Response.Listener {
            response: JSONObject ->

        }, Response.ErrorListener {

        })
    }
}