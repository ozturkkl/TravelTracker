package uc.edu.ozturkkl.traveltracker.services

import androidx.lifecycle.MutableLiveData
import uc.edu.ozturkkl.traveltracker.dto.PredictionResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uc.edu.ozturkkl.traveltracker.RetrofitClientInstance
import uc.edu.ozturkkl.traveltracker.dao.PlaceAutoCompleteAPI

class LocationService {

    fun loadPredictions(address: String?) : MutableLiveData<ArrayList<PredictionResponse>> {
        var _locations = MutableLiveData<ArrayList<PredictionResponse>>()
        val service = RetrofitClientInstance.retrofitInstance?.create(PlaceAutoCompleteAPI::class.java)
        val call = service?.loadPredictions(address)
        call?.enqueue(object: Callback<ArrayList<PredictionResponse>> {

            override fun onFailure(call: Call<ArrayList<PredictionResponse>>, t: Throwable) {
                print("Error at Retrofit thread enqueue in Location Service.")
            }

            override fun onResponse(
                call: Call<ArrayList<PredictionResponse>>,
                response: Response<ArrayList<PredictionResponse>>
            ) {
                if (Response.isSuccessful()) {
                    _locations.value = response.body()
                }
            }
        })

        return _locations
    }
}