package com.anismhub.ticketsystem.di

import android.content.Context
import com.anismhub.ticketsystem.BuildConfig
import com.anismhub.ticketsystem.data.manager.LocalDataManagerImpl
import com.anismhub.ticketsystem.data.remote.ApiService
import com.anismhub.ticketsystem.data.repository.AuthRespositoryImpl
import com.anismhub.ticketsystem.data.repository.TicketRepositoryImpl
import com.anismhub.ticketsystem.domain.manager.LocalDataManager
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.domain.repository.TicketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDataManager(
        @ApplicationContext context: Context
    ): LocalDataManager = LocalDataManagerImpl(context = context)

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        apiService: ApiService,
        localDataManager: LocalDataManager
    ): AuthRepository = AuthRespositoryImpl(
        apiService = apiService,
        localDataManager = localDataManager
    )

    @Provides
    @Singleton
    fun provideTicketRepository(apiService: ApiService): TicketRepository =
        TicketRepositoryImpl(apiService = apiService)
}