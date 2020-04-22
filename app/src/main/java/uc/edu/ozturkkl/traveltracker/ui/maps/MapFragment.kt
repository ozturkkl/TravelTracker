package uc.edu.ozturkkl.traveltracker.ui.maps

import android.Manifest
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

import uc.edu.ozturkkl.traveltracker.R
import uc.edu.ozturkkl.traveltracker.ui.addplace.AddPlaceActivity
import uc.edu.ozturkkl.traveltracker.ui.main.LocationViewModel
import uc.edu.ozturkkl.traveltracker.ui.main.MainViewModel
import java.lang.Float.parseFloat
import java.util.*


class MapFragment : Fragment() {

    private lateinit var mMap : GoogleMap
    private lateinit var locationViewModel: LocationViewModel
    private val LOCATION_REQUEST_CODE = 2000

    companion object {
        fun newInstance() = MapFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var rootView = inflater.inflate(R.layout.maps_fragment, container, false)

        val autocompleteFragment = childFragmentManager.findFragmentById(R.id.autocomplete_fragment) as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.RATING));
        autocompleteFragment.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
                var mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync {
                        googleMap -> mMap = googleMap

                    mMap = googleMap
                    val location = place.latLng!!
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
                }
                val intent = Intent(activity, AddPlaceActivity::class.java)
                if(place.name  != null){
                    intent.putExtra("PLACE_NAME", place.name.toString())
                }
                if(place.rating != null){
                    intent.putExtra("PLACE_RATING", place.rating.toString())
                }
                if(place.latLng != null){
                    var latLng = place.latLng.toString().substring(10, place.latLng.toString().length - 1).split(",")
                    var latitude = latLng[0]
                    var longitude = latLng[1]
                    intent.putExtra("PLACE_LAT", latitude)
                    intent.putExtra("PLACE_LONG", longitude)
                }
                startActivity(intent)
            }

            override fun onError(status: Status) {
                Log.i(TAG, "An error occurred: $status")
            }

            fun onError(status: AsyncTask.Status) {
                Log.i(TAG, "An error occurred: $status")
            }
        })

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewModel.locations.observe(this, androidx.lifecycle.Observer{
            locations ->
            locations.forEach{
                val color = null
                var mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
                mapFragment.getMapAsync {
                        googleMap -> mMap = googleMap

                    mMap = googleMap
                    val location = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
                    if(it.haveVisited){
                        mMap.addMarker(
                            MarkerOptions()
                                .position(location)
                                .title(it.name)
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
                    }
                    else{
                        mMap.addMarker(
                            MarkerOptions()
                                .position(location)
                                .title(it.name))
                    }
                }
            }
        })
        prepRequestLocationUpdates()
    }

    private fun prepRequestLocationUpdates() {
        if(ContextCompat.checkSelfPermission(context!!, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            requestLocationUpdates()
        }
        else {
            val permissionRequest = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(permissionRequest, LOCATION_REQUEST_CODE)
        }
    }

    private fun requestLocationUpdates() {
        locationViewModel = ViewModelProviders.of(this).get(LocationViewModel::class.java)
        locationViewModel.getLocationLiveData().observe(this, androidx.lifecycle.Observer {
            val latitude = it.latitude
            val longitude = it.longitude
            var mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
            mapFragment.getMapAsync {
                    googleMap -> mMap = googleMap

                mMap = googleMap
                val location = LatLng(latitude.toDouble(), longitude.toDouble())
                mMap.addCircle(CircleOptions().center(location).radius(50000.0).strokeColor(Color.BLUE).fillColor(Color.BLUE))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location))
            }
        })
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when(requestCode) {
            LOCATION_REQUEST_CODE -> {
                if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    requestLocationUpdates()
                }
                else {
                    Toast.makeText(context, "Unable to get location without permission", Toast.LENGTH_LONG)
                }
            }
        }
    }

}
