package uc.edu.ozturkkl.traveltracker.locationServiceStub

import androidx.lifecycle.MutableLiveData
import uc.edu.ozturkkl.traveltracker.dto.Location
import uc.edu.ozturkkl.traveltracker.dao.LocationDAO

class LocationStub {
    //test case we can use to sift through a mutable list of locations
    internal fun fetchLocations(locationName : String) : MutableList<Location> {
        var _locations = MutableList(20) { Location("5433 Waterfall Hills", "Cincinnati", "Ohio", 45244) }
        _locations.filter { it.address == locationName}
        return _locations
    }
}