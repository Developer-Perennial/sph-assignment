package co.test.sphtestapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import co.test.sphtestapp.data.network.response.Record

@Database(
    entities = [Record::class],
    version = 1,
    exportSchema = false
)
abstract class DatastoreDatabase : RoomDatabase() {
    abstract fun datastoreDao(): DatastoreDao
}