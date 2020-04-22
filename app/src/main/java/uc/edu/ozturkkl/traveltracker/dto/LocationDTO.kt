package uc.edu.ozturkkl.traveltracker.dto

data class LocationDTO(val name : String = "", val haveVisited : Boolean = false, val rating : Float = 0.0f, val description : String = "", val longitude : String = "", val latitude: String = "", var id : String = "") {
}