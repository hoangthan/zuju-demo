package com.zuju.ibraries.match.data.network

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
class MatchNetworkModule {

    @Provides
    @Singleton
    fun provideTickerService(
        converterFactory: Converter.Factory,
        okHttpClient: OkHttpClient,
    ): MatchApiService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .baseUrl(NetworkResource.BASE_URL)
            .build()
            .create(MatchApiService::class.java)
    }
}
