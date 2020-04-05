package uc.edu.ozturkkl.traveltracker.dao

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import uc.edu.ozturkkl.traveltracker.dto.PredictionResponse


interface PlaceAutoCompleteAPI {
    /*
    * @Query() appends the parameter to the HTTP request.
    * In this case, the request made by retrofit looks like
    * BASEURL/api/place/autocomplete/json?types=address&key=YOUR-KEY&input=addressFromUser
    */
    @GET("api/place/autocomplete/json?types=address&key=YOUR-KEY")
    fun loadPredictions(@Query("input") address: String?): Call<ArrayList<PredictionResponse>>
}