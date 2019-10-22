package com.pascalhow.marsdiscovery.activities.mars

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.pascalhow.marsdiscovery.data.repo.MarsRepository
import com.pascalhow.marsdiscovery.data.resource.Resource
import com.pascalhow.marsdiscovery.data.resource.ResourceState
import com.pascalhow.marsdiscovery.domain.UseCase
import io.reactivex.subscribers.DisposableSubscriber

class MarsViewModel(
    private val marsFootageUseCase: MarsFootageUseCase
) : ViewModel() {

    private val marsFootageListLiveData: MutableLiveData<Resource<List<MarsFootage>>> =
        MutableLiveData()

    init {
        fetchMarsFootageList()
    }

    fun getMarsFootageListLiveData(): LiveData<Resource<List<MarsFootage>>> =
        marsFootageListLiveData

    private fun fetchMarsFootageList() {
        marsFootageListLiveData.postValue(Resource(ResourceState.Loading, null, null))

        marsFootageUseCase.execute(UseCase.None) {
            marsFootageListLiveData.postValue(Resource(ResourceState.Success, it, null))
        }
    }

    fun dispose() {
        marsFootageUseCase.cleanUp()
    }

}

class MarsViewModelFactory(
    private val marsFootageUseCase: MarsFootageUseCase
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MarsViewModel(marsFootageUseCase) as T
    }
}
