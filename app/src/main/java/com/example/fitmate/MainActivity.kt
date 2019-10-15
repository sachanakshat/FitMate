package com.example.fitmate

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.aqi_data.*
import kotlinx.android.synthetic.main.health_feed.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity(){

    var volleyReq: RequestQueue? = null
    var coordinates:String? = "28.605280;77.105402"
    var string:String? = null

    private var permission = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)

    var url = "https://api.waqi.info/feed/geo:$coordinates/?token=8b13f1ca1e0c3d60b7cf452c526419c63cbc9e71"

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {



        val w = window
        w.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        w.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission()
        }
        volleyReq = Volley.newRequestQueue(this)
        aqi_data.isClickable = true
        aqi_data.setOnClickListener{
            getLocation()
        }
        var newList = arrayListOf("hello","Hello")
        var adapter = HealthFeedAdapter(this,newList,newList)
        bmiButton.setOnClickListener{
            startActivity(Intent(this,bmiCheck::class.java))
        }

    }

    @SuppressLint("MissingPermission")
    fun getLocation(){
        var locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        var locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                if (location != null) {
                    coordinates = "${location!!.longitude};${location!!.latitude}"
                    url = "https://api.waqi.info/feed/geo:" + coordinates + "/?token=8b13f1ca1e0c3d60b7cf452c526419c63cbc9e71";
                    getJSONObject(url)
                }
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

            override fun onProviderEnabled(p0: String?) {}

            override fun onProviderDisabled(p0: String?) {}
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10f, locationListener)
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                permission,
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
        return
    }

    private fun getJSONObject(url: String){
        var req = JsonObjectRequest(Request.Method.GET,url,Response.Listener {
            response: JSONObject ->
            try {
                var data = response.getJSONObject("data")
                data = data.getJSONObject("iaqi")
                //var dom = data.get()
                data = data.getJSONObject("pm25")
                var value = data.getInt("v")
                string = "PM2.5: $value"
                levels.text = string
                Log.d("Response: ", response.toString())
                //Log.d("PM2.5: ",value.toString())

            } catch (e: JSONException){
                e.printStackTrace()
            }
        },Response.ErrorListener {
        })
        volleyReq!!.add(req)
    }
}