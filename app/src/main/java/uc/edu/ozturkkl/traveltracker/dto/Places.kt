package uc.edu.ozturkkl.traveltracker.dto

data class Places(var Country: String = "", var Journal:String = "", var Rating: Int = 0, var State:String = "") {
    override fun toString(): String {
        return "$Country $Journal $State"
    }
}

data class Restaurants(var Name: String = "", var Address: String = "", var Rating: Int = 0) {
    override fun toString(): String {
        return "$Name $Address $Rating"
    }
}

data class Entertainments(var Name: String = "", var Address: String = "", var Age: String = "", var Rating: Int = 0){
    override fun toString(): String {
        return "$Name $Address $Rating"
    }
}

data class Gas(var Name: String = "", var Address: String = "", var Price: Double = 0.0, var PaymentType: String = "" ) {
    override fun toString(): String {
        return "$Name $Address, $Price, $PaymentType"
    }
}
