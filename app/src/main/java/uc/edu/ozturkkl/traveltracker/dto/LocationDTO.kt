package uc.edu.ozturkkl.traveltracker.dto
import android.gesture.Prediction
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PredictionResponse {
    @SerializedName("predictions")
    @Expose
    var predictions: List<Prediction>? = null
    @SerializedName("status")
    @Expose
    var status: String? = null
}