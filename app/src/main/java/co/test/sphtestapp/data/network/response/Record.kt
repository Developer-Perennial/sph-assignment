package co.test.sphtestapp.data.network.response

data class Record(
    val _id: Int,
    val quarter: String,
    val volume_of_mobile_data: String,
    var year: String,
    var total_volume_data: String
)