package uc.edu.ozturkkl.traveltracker.ui.toVisit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.to_visit_fragment.*

import uc.edu.ozturkkl.traveltracker.R
import uc.edu.ozturkkl.traveltracker.ui.main.MainActivity

/**
 * A simple [Fragment] subclass.
 */
class ToVisitFragment : Fragment() {

    companion object {
        fun newInstance() = ToVisitFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var toVisitView = inflater.inflate(R.layout.to_visit_fragment, container, false)

        return toVisitView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addPlaceBtn.setOnClickListener(){
            MainActivity().showAddPlaceScreen()
        }
    }

}
