package uc.edu.ozturkkl.traveltracker.ui.addplace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.google.type.LatLng
import kotlinx.android.synthetic.main.add_place_activity.*
import uc.edu.ozturkkl.traveltracker.R
import uc.edu.ozturkkl.traveltracker.dto.LocationDTO
import uc.edu.ozturkkl.traveltracker.ui.main.MainViewModel
import uc.edu.ozturkkl.traveltracker.ui.maps.MapFragment
import kotlin.properties.Delegates


class AddPlaceActivity : AppCompatActivity() {

    private lateinit var name : String
    private var rating by Delegates.notNull<Float>()
    private lateinit var lat : String
    private lateinit var long  : String
    private var haveVisited  : Boolean = false
    private lateinit var description : String

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.add_place_activity)

        //retrieve Place data and populate form
        if(intent.hasExtra("PLACE_NAME")){
            name = intent.getStringExtra("PLACE_NAME")!!
            locationNameTxt.setText(name)

            if(intent.hasExtra("PLACE_RATING")){
                rating = intent.getStringExtra("PLACE_RATING")!!.toFloat()
                locationRating.rating = rating
            }
            if(intent.hasExtra("PLACE_LAT") && intent.hasExtra("PLACE_LONG")){
                lat = intent.getStringExtra("PLACE_LAT")!!
                long = intent.getStringExtra("PLACE_LONG")!!
            }
        }

        saveBtn.setOnClickListener(){
            saveLocation()
        }

        closeBtn.setOnClickListener(){
            finish()
        }
    }

    private fun saveLocation() {
        //initialize any variables that weren't passed into the intent
        description = locationDescTxt.text.toString()
        rating = locationRating.rating
        if(hasVisitedSwitch.isChecked){
            haveVisited = true
        }

        //populate the DTO object
        var location = LocationDTO(name, haveVisited, rating, description, long, lat)

        //save location
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.new(location)

        finish()
    }


}
