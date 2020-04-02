package uc.edu.ozturkkl.traveltracker.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.ozturkkl.traveltracker.dto.PlacesDTO
import uc.edu.ozturkkl.traveltracker.dto.PredictionResponse
import uc.edu.ozturkkl.traveltracker.services.LocationService

class MainViewModel : ViewModel() {
    private var _locations: MutableLiveData<ArrayList<PredictionResponse>> = MutableLiveData()
    var address: String = String()
    var locationService = LocationService();
    private lateinit var firestore : FirebaseFirestore

    init {
        loadPredictions(address)
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
    }

    fun loadPredictions(address : String) {
        _locations = locationService.loadPredictions(address);
    }

    fun save(placesDTO: PlacesDTO) {
        firestore.collection("Places")
            .document()
            .set(placesDTO)
            .addOnSuccessListener{
                Log.d("Firebase", "document saved")
            }
            .addOnFailureListener{
                Log.d("Firebase", "Saved Failed")
            }
    }

    internal var locations: MutableLiveData<ArrayList<PredictionResponse>>
        get() {return _locations}
        set(value) {_locations = value}
}
