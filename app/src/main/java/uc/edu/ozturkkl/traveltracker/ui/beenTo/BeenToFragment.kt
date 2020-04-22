package uc.edu.ozturkkl.traveltracker.ui.beenTo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.been_to_fragment.*
import uc.edu.ozturkkl.traveltracker.R
import uc.edu.ozturkkl.traveltracker.ui.main.MainViewModel


class BeenToFragment : Fragment() {

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(context != null){

            viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            viewModel.locations.observe(this, androidx.lifecycle.Observer{
                    locations ->
                locations.forEach{
                    val card = CardView(context!!)

                    card.layoutParams =
                        ConstraintLayout
                            .LayoutParams(
                                ConstraintLayout.LayoutParams.MATCH_PARENT,
                                ConstraintLayout.LayoutParams.WRAP_CONTENT
                            )

                    val locationTextView = TextView(context!!)
                    locationTextView.layoutParams =
                        ConstraintLayout
                            .LayoutParams(
                                ConstraintLayout.LayoutParams.MATCH_PARENT,
                                ConstraintLayout.LayoutParams.WRAP_CONTENT
                            )
                    locationTextView.text = it.name

                    val ratingBar = RatingBar(context!!)
                    locationTextView.layoutParams =
                        ConstraintLayout
                            .LayoutParams(
                                ConstraintLayout.LayoutParams.MATCH_PARENT,
                                ConstraintLayout.LayoutParams.WRAP_CONTENT
                            )
                    ratingBar.numStars = 5
                    ratingBar.rating = it.rating

                    val descriptionTextView = TextView(context!!)
                    locationTextView.layoutParams =
                        ConstraintLayout
                            .LayoutParams(
                                ConstraintLayout.LayoutParams.MATCH_PARENT,
                                ConstraintLayout.LayoutParams.WRAP_CONTENT
                            )
                    descriptionTextView.text = it.description

                    card.addView(locationTextView)
                    card.addView(ratingBar)
                    card.addView(descriptionTextView)
                    card_layout.addView(card)
                }
            })
        }

    }
}

