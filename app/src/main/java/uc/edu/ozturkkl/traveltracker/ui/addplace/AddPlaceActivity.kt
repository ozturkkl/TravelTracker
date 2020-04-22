package uc.edu.ozturkkl.traveltracker.ui.addplace

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.add_place_activity.*
import uc.edu.ozturkkl.traveltracker.R

class AddPlaceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_place_activity)

        closeBtn.setOnClickListener(){
            finish()
        }
    }



}
