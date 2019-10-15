package com.example.fitmate

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity(){

    var requestQueue: RequestQueue? = null
    var coordinates:String? = "28.605280;77.105402"
    var gpsLocation: Location? = null
    var networkLocation: Location? = null

    private var permission = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)

    var url:String? = null

    companion object {
        private const val PERMISSION_REQUEST_ACCESS_FINE_LOCATION = 100
    }

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
                    gpsLocation = location
                    coordinates = "${location.longitude};${location.latitude}"
                }
            }

            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}

            override fun onProviderEnabled(p0: String?) {}

            override fun onProviderDisabled(p0: String?) {}
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermission()
        }

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0f,locationListener)
    }

    fun requestPermission() {
        ActivityCompat.requestPermissions(
                this,
                permission,
                PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
        return
    }

    fun getData(){
        var aqiReceiver = JsonObjectRequest(Request.Method.GET,url,Response.Listener {
            response: JSONObject ->
            try {
                var data = response.getJSONObject("data")
                var iaqi = data.getJSONObject("iaqi")
                var pm25 = iaqi.getJSONObject("pm25")
                var value = pm25.getInt("v")
            }catch (e: Exception){
                e.printStackTrace()
            }

        }, Response.ErrorListener {

        })

        requestQueue!!.add(aqiReceiver)
    }
}