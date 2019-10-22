package com.pascalhow.marsdiscovery.data.repo

import android.content.Context
import com.pascalhow.marsdiscovery.rest.network.RestClient
import com.pascalhow.marsdiscovery.utils.NetworkStatusProvider

class MarsDataStoreFactory(
    private val context: Context,
    private val networkStatusProvider: NetworkStatusProvider
) {

    fun create(): MarsDataStore {
//        return if (networkStatusProvider.hasNetworkConnection()) {
//            CloudMarsDataStore(context, RestClient)
//        } else {
//            DiskMarsDataStore(context)
//        }

        return CloudMarsDataStore(context, RestClient)
    }
}
