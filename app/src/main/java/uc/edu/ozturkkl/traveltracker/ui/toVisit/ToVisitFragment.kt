package uc.edu.ozturkkl.traveltracker.ui.toVisit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.to_visit_fragment.*

import uc.edu.ozturkkl.traveltracker.R
import uc.edu.ozturkkl.traveltracker.dto.LocationDTO
import uc.edu.ozturkkl.traveltracker.ui.addplace.AddPlaceActivity
import uc.edu.ozturkkl.traveltracker.ui.main.MainActivity
import uc.edu.ozturkkl.traveltracker.ui.main.MainViewModel

/**
 * A simple [Fragment] subclass.
 */
class ToVisitFragment : Fragment() {

    companion object {
        fun newInstance() = ToVisitFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var toVisitView = inflater.inflate(R.layout.to_visit_fragment, container, false)

        return toVisitView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(context != null){

            viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            viewModel.locations.observe(this, androidx.lifecycle.Observer{
                    locations ->
                var toVisitLocations:ArrayList<LocationDTO> = ArrayList<LocationDTO>()
                locations.forEach{
                    if (!it.haveVisited){
                        toVisitLocations.add(it)
                    }
                }
                toVisitList.adapter = CustomAdapter(toVisitList.context ,toVisitLocations)
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
