package com.pascalhow.marsdiscovery.rest.network

import com.pascalhow.marsdiscovery.rest.model.MarsSearchResults
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsApiService {

    @GET("search")
    fun request(
        @Query("q") search: String,
        @Query("media_type") mediaType: String
    ): Observable<MarsSearchResults>

    companion object {
        private const val BASE_URL = "https://images-api.nasa.gov/"

        fun create(): MarsApiService {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(MarsApiService::class.java)
        }
    }
}
