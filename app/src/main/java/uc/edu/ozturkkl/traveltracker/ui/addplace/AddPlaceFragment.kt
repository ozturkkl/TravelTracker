package uc.edu.ozturkkl.traveltracker.ui.addplace

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import uc.edu.ozturkkl.traveltracker.R

/**
 * A simple [Fragment] subclass.
 */
class AddPlaceFragment : Fragment() {

    companion object {
        fun newInstance() = AddPlaceFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.add_place_fragmet, container, false)
    }

}
