package uc.edu.ozturkkl.traveltracker.ui.beenTo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.been_to_fragment.*
import uc.edu.ozturkkl.traveltracker.R
import uc.edu.ozturkkl.traveltracker.ui.addplace.AddPlaceActivity


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addPlaceBtn.setOnClickListener(){
            val intent = Intent(activity, AddPlaceActivity::class.java)
            startActivity(intent)
        }
    }
}

