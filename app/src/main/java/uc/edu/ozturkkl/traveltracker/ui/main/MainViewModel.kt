package uc.edu.ozturkkl.traveltracker.ui.main

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import uc.edu.ozturkkl.traveltracker.dto.LocationDTO

class MainViewModel : ViewModel() {

    private lateinit var firestore : FirebaseFirestore
    private var _locations : MutableLiveData<ArrayList<LocationDTO>> = MutableLiveData<ArrayList<LocationDTO>>()

    init {
        firestore = FirebaseFirestore.getInstance()
        firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        listenToLocations()
    }

    private fun listenToLocations() {
        firestore.collection("Locations").addSnapshotListener{
            snapshot, e ->
            if(e != null){
                Log.w(TAG, "Listen Failed", e)
                return@addSnapshotListener
            }
            if(snapshot != null){
                val allLocations = ArrayList<LocationDTO>()
                val documents = snapshot.documents
                documents.forEach{
                    val location = it.toObject(LocationDTO::class.java)
                    if(location != null){
                        allLocations.add(location!!)
                    }
                }
                _locations.value = allLocations
            }
        }
    }

    fun new(location: LocationDTO) {
        val document = firestore.collection("Locations").document()
        location.id = document.id
            document.set(location)
            .addOnSuccessListener{
                Log.d("Firebase", "document saved")
                return@addOnSuccessListener
            }
            .addOnFailureListener{
                Log.d("Firebase", "Saved Failed")
                return@addOnFailureListener
            }
    }

    fun update(location: LocationDTO) {
        val document = firestore.collection("Locations").document(location.id)
        location.id = document.id
        document.set(location)
            .addOnSuccessListener{
                Log.d("Firebase", "document saved")
            }
            .addOnFailureListener{
                Log.d("Firebase", "Saved Failed")
            }
    }

    internal var locations : MutableLiveData<ArrayList<LocationDTO>>
        get() {return _locations}
        set(value) {_locations = value}

}
