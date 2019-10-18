package com.pascalhow.marsdiscovery.rest.network

import com.pascalhow.marsdiscovery.rest.model.MarsSearchResults
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsApiService {

    @GET("search")
    fun request(
        @Query("q") search: String,
        @Query("media_type") mediaType: String
    ): Call<MarsSearchResults>

    companion object {
        private const val BASE_URL = "https://images-api.nasa.gov/"

        fun create(): MarsApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(MarsApiService::class.java)
        }
    }
}
