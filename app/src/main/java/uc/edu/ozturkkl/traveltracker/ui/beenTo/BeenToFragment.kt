package uc.edu.ozturkkl.traveltracker.ui.beenTo

import android.content.Context
import android.media.Rating
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.been_to_fragment.*
import kotlinx.android.synthetic.main.location_template.*
import uc.edu.ozturkkl.traveltracker.R
import uc.edu.ozturkkl.traveltracker.dto.LocationDTO
import uc.edu.ozturkkl.traveltracker.ui.main.MainViewModel


class BeenToFragment : Fragment() {

    private lateinit var locations:ArrayList<LocationDTO>

    companion object {
        fun newInstance() = BeenToFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.been_to_fragment, container, false)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if(context != null){

            viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            viewModel.locations.observe(this, androidx.lifecycle.Observer{
                    locations ->
                this.locations = locations
                beenToList.adapter = CustomAdapter(beenToList.context ,locations)

            })
        }

    }

    private class CustomAdapter(context: Context?, locations: ArrayList<LocationDTO>): BaseAdapter() {
        private val locations: ArrayList<LocationDTO> = locations
        private val context: Context? = context

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(context)
            val locationTemplate = inflater.inflate(R.layout.location_template, parent, false)

            val location = locationTemplate.findViewById<TextView>(R.id.lblLocation)
            location.text = locations[position].name

            val description = locationTemplate.findViewById<TextView>(R.id.txtDescription)
            description.text = locations[position].description

            val rating = locationTemplate.findViewById<RatingBar>(R.id.ratingLocation)
            rating.rating = locations[position].rating

            return locationTemplate
        }

        override fun getItem(position: Int): Any {
            return "test string"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return locations.size
        }

    }
}

