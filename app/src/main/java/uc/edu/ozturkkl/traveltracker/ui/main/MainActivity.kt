package uc.edu.ozturkkl.traveltracker.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.libraries.places.api.Places
import kotlinx.android.synthetic.main.main_activity.*
import uc.edu.ozturkkl.traveltracker.R
import uc.edu.ozturkkl.traveltracker.ui.addplace.AddPlaceActivity
import uc.edu.ozturkkl.traveltracker.ui.beenTo.BeenToFragment
import uc.edu.ozturkkl.traveltracker.ui.maps.MapFragment
import uc.edu.ozturkkl.traveltracker.ui.toVisit.ToVisitFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MapFragment.newInstance())
            .commitNow()

        mapBtn.setOnClickListener(){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MapFragment.newInstance())
                .commitNow()
        }

        beenToBtn.setOnClickListener(){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BeenToFragment.newInstance())
                .commitNow()
        }

        toVisitBtn.setOnClickListener(){
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ToVisitFragment.newInstance())
                .commitNow()
        }



        val apiKey = getString(R.string.google_maps_key)

        // Setup Places Client
        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, apiKey)
        }
    }

    fun showAddPlaceScreen(){
    }
}
