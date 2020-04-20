package uc.edu.ozturkkl.traveltracker.dto

class PlacesDTO(var location: String = "", var description:String = "", var rating:Int = 0) {
    override fun toString(): String {
        return "$location $description $rating"
    }
}