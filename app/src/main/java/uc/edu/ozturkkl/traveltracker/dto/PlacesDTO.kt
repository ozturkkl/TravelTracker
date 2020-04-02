package uc.edu.ozturkkl.traveltracker.dto

class PlacesDTO(var location: String = "", var description:String = "") {
    override fun toString(): String {
        return "$location $description"
    }
}