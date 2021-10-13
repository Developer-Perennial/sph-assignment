package co.test.sphtestapp.di

import android.content.Context
import androidx.room.Room
import co.test.sphtestapp.common.Constants.Database.Companion.DATABASE_NAME
import co.test.sphtestapp.common.Constants.Urls.Companion.BASE_URL
import co.test.sphtestapp.data.local.DatastoreDao
import co.test.sphtestapp.data.local.DatastoreDatabase
import co.test.sphtestapp.data.network.DataStoreClient
import co.test.sphtestapp.repository.DatastoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatastoreDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, DatastoreDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideDatastoreDao(
        database: DatastoreDatabase
    ) = database.datastoreDao()

    @Singleton
    @Provides
    fun provideDatastoreRepository(
        dataStoreClient: DataStoreClient,
        datastoreDao: DatastoreDao
    ) = DatastoreRepository(dataStoreClient, datastoreDao)

    @Singleton
    @Provides
    fun provideDataStoreClient(): DataStoreClient {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(DataStoreClient::class.java)
    }
}

















