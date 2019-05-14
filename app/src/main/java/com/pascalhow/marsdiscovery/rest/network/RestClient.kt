package com.pascalhow.marsdiscovery.rest.network

import com.pascalhow.marsdiscovery.rest.model.MarsSearchResults
import io.reactivex.Observable

object RestClient {

    private val marsApiService by lazy {
        MarsApiService.create()
    }

    fun search(planet: String, mediaType: String): Observable<MarsSearchResults> {
        return marsApiService.request(planet, mediaType)
    }
}
