package uc.edu.ozturkkl.traveltracker.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_fragment.*
import uc.edu.ozturkkl.traveltracker.R
import uc.edu.ozturkkl.traveltracker.dto.PlacesDTO

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    private fun savePlaces() {
        var places = PlacesDTO().apply {
            location = lblLocation.text.toString()
            description =txtDescription.text.toString()
            rating = ratingLocation.rating.toInt()
        }
        viewModel.save(places)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        savePlaces()
        // TODO: Use the ViewModel
    }

}


