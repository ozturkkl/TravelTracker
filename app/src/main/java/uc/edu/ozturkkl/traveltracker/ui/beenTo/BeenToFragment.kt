package uc.edu.ozturkkl.traveltracker.ui.beenTo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uc.edu.ozturkkl.traveltracker.R


class BeenToFragment : Fragment() {

    companion object {
        fun newInstance() = BeenToFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.been_to_fragment, container, false)
        }
}

