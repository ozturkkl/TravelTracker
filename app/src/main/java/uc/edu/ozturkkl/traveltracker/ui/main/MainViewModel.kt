package uc.edu.ozturkkl.traveltracker.ui.main

import androidx.lifecycle.ViewModel
import uc.edu.ozturkkl.traveltracker.dto.Location
import uc.edu.ozturkkl.traveltracker.locationServiceStub.LocationStub

class MainViewModel : ViewModel() {
    private var _locations: MutableList<Location> = mutableListOf()
    var locationService: LocationStub = LocationStub()
    var locationName: String = String()

    init {
        fetchLocations(locationName)
    }

    fun fetchLocations(locationName : String) {
        _locations = locationService.fetchLocations(locationName)
    }

    internal var locations:MutableList<Location>
        get() {return _locations}
        set(value) {_locations = value}
}
