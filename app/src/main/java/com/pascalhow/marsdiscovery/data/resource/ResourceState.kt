package com.pascalhow.marsdiscovery.data.resource

sealed class ResourceState {
    object Success : ResourceState()
    object Error : ResourceState()
    object Loading : ResourceState()
}
