package co.test.sphtestapp.data.network.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "datastore_records")
data class Record(
    @PrimaryKey
    val _id: Int,
    val quarter: String,
    val volume_of_mobile_data: String,
    @Ignore var year: String,
    @Ignore var total_volume_data: String
) : Parcelable {
    constructor(_id: Int, quarter: String, volume_of_mobile_data: String) : this(
        _id,
        quarter,
        volume_of_mobile_data,
        "",
        "0.0"
    )
}