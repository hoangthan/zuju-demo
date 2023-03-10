package com.zuju.data.teamplayer.data.network

import com.zuju.libraries.resource.NetworkResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class TeamPlayerNetworkModule {

    @Provides
    @Singleton
    fun provideTickerService(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient,
    ): TeamPlayerApiService {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .baseUrl(NetworkResource.BASE_URL)
            .build()
            .create(TeamPlayerApiService::class.java)
    }
}
