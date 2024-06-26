package com.anismhub.ticketsystem.di

import android.content.Context
import com.anismhub.ticketsystem.BuildConfig
import com.anismhub.ticketsystem.data.manager.LocalDataManagerImpl
import com.anismhub.ticketsystem.data.remote.ApiService
import com.anismhub.ticketsystem.data.repository.AuthRepositoryImpl
import com.anismhub.ticketsystem.data.repository.ResourcesRepositoryImpl
import com.anismhub.ticketsystem.data.repository.TicketRepositoryImpl
import com.anismhub.ticketsystem.domain.manager.LocalDataManager
import com.anismhub.ticketsystem.domain.repository.AuthRepository
import com.anismhub.ticketsystem.domain.repository.ResourcesRepository
import com.anismhub.ticketsystem.domain.repository.TicketRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
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
    fun provideApiService(
        authInterceptor: Interceptor
    ): ApiService {
        val loggingInterceptor = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        } else {
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
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
    ): AuthRepository = AuthRepositoryImpl(
        apiService = apiService,
        localDataManager = localDataManager
    )

    @Provides
    @Singleton
    fun provideAuthInterceptor(localDataManager: LocalDataManager): Interceptor =
        Interceptor { chain ->
            val token = runBlocking { localDataManager.getAccessToken().first() }
            val request = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
            chain.proceed(request)
        }

    @Provides
    @Singleton
    fun provideTicketRepository(
        apiService: ApiService,
        @ApplicationContext context: Context
    ): TicketRepository =
        TicketRepositoryImpl(apiService = apiService, context = context)

    @Provides
    @Singleton
    fun provideResourcesRepository(apiService: ApiService): ResourcesRepository =
        ResourcesRepositoryImpl(apiService = apiService)
}