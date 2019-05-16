package com.pascalhow.marsdiscovery.data.resource

class Resource<out T>(val status: ResourceState, val data: T?, val message: String?) {

    fun <T> success(data: T): Resource<T> {
        return Resource(ResourceState.Success, data, null)
    }

    fun <T> error(data: T): Resource<T> {
        return Resource(ResourceState.Error, data, null)
    }

    fun <T> loading(data: T): Resource<T> {
        return Resource(ResourceState.Loading, data, null)
    }

}
