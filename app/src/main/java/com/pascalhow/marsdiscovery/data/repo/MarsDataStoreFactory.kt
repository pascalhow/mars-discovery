package com.pascalhow.planetmars.data.repo

import android.content.Context
import com.pascalhow.planetmars.utils.NetworkStatusProvider
import com.pascalhow.planetmars.rest.network.RestClient

class MarsDataStoreFactory(
    private val context: Context,
    private val networkStatusProvider: NetworkStatusProvider
) {

    fun create(): MarsDataStore {
        return if (networkStatusProvider.hasNetworkConnection()) {
            CloudMarsDataStore(context, RestClient)
        } else {
            DiskMarsDataStore(context)
        }
    }
}
