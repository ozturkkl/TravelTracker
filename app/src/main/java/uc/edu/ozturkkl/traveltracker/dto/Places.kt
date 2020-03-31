package uc.edu.ozturkkl.traveltracker.dto

data class Places(var Country: String = "", var Journal:String = "", var Rating: Int = 0, var State:String = "") {
    override fun toString(): String {
        return "$Country $Journal $State"
    }
}