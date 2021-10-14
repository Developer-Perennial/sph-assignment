package co.test.sphtestapp.data.local

import androidx.room.*
import co.test.sphtestapp.data.network.response.Record

@Dao
interface DatastoreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatastoreRecords(data: ArrayList<Record>): Array<Long>

    @Query("SELECT * FROM datastore_records")
    fun findDatastoreRecords(): List<Record>

    @Query("DELETE FROM datastore_records")
    fun deleteDatastoreRecords()

    @Query("SELECT * FROM datastore_records WHERE quarter LIKE :year || '%'")
    fun findDatastoreYearRecords(year: String): List<Record>

}