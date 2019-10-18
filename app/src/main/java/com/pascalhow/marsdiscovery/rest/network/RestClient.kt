package com.pascalhow.marsdiscovery.rest.network

import com.pascalhow.marsdiscovery.rest.model.MarsSearchResults
import retrofit2.Call

object RestClient {

    private val marsApiService by lazy {
        MarsApiService.create()
    }

    fun search(planet: String, mediaType: String): Call<MarsSearchResults> {
        return marsApiService.request(planet, mediaType)
    }
}
