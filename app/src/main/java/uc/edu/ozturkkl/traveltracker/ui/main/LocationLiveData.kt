package uc.edu.ozturkkl.traveltracker.ui.main

import android.content.Context
import android.location.Location
import androidx.lifecycle.LiveData
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import uc.edu.ozturkkl.traveltracker.dto.LocationDetails

class LocationLiveData(context: Context) : LiveData<LocationDetails>() {

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    override fun onActive() {
        super.onActive()

        fusedLocationClient.lastLocation.addOnSuccessListener {
                location: Location -> location.also{
            setLocationData(it)
        }
        }
        startLocationUpdates()
    }

    override fun onInactive() {
        super.onInactive()

       fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun startLocationUpdates() {
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)

            locationResult ?: return

            for (location in locationResult.locations) {
                setLocationData(location)
            }
        }
    }

    private fun setLocationData(location: Location) {
        value = LocationDetails(location.longitude.toString(), location.latitude.toString())
    }

    companion object {
        val ONE_MINUTE : Long = 60000
        val locationRequest : LocationRequest = LocationRequest.create().apply{
            interval = ONE_MINUTE
            fastestInterval = ONE_MINUTE / 4
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

    }
}