package co.test.sphtestapp.di

import co.test.sphtestapp.common.Constants.Urls.Companion.BASE_URL
import co.test.sphtestapp.data.network.DataStoreClient
import co.test.sphtestapp.repository.DatastoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDatastoreRepository(
        dataStoreClient: DataStoreClient
    ) = DatastoreRepository(dataStoreClient)

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

















