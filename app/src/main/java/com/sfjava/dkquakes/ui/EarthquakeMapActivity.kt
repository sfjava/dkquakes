package com.sfjava.dkquakes.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.sfjava.dkquakes.R

class EarthquakeMapActivity: AppCompatActivity(), OnMapReadyCallback {

    val args: EarthquakeMapActivityArgs by navArgs()
    lateinit var latLng: LatLng
    val INITIAL_ZOOM_LEVEL = 5f // NOTE: a zoom level of 4 or 5 is good to visualize quake location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.earthquake_map_activity)

        // get the location of the earthquake from arguments supplied
        latLng = LatLng(args.latitude.toDouble(), args.longitude.toDouble())

        val mapFragment : SupportMapFragment? =
            supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    /**
     * map is ready: here we can add markers or lines, add listeners or move the camera
     */
    override fun onMapReady(map: GoogleMap?) {
        map ?: return // TODO: when map fails to load... handle gracefully ??
        with(map) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, INITIAL_ZOOM_LEVEL))
            addMarker(MarkerOptions().position(latLng))
        }
    }
}
