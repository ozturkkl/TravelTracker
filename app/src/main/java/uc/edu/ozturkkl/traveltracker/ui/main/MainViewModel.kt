package uc.edu.ozturkkl.traveltracker.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.ozturkkl.traveltracker.dto.Places
import uc.edu.ozturkkl.traveltracker.dto.PredictionResponse
import uc.edu.ozturkkl.traveltracker.services.LocationService

class MainViewModel : ViewModel() {
    private var _locations: MutableLiveData<ArrayList<PredictionResponse>> = MutableLiveData()
    var address: String = String()
    var locationService = LocationService();
    private var firestore : FirebaseFirestore

    init {
        loadPredictions(address)
        firestore = FirebaseFirestore.getInstance()

    }

    fun loadPredictions(address : String) {
        _locations = locationService.loadPredictions(address);
    }

    internal var locations: MutableLiveData<ArrayList<PredictionResponse>>
        get() {return _locations}
        set(value) {_locations = value}
}
