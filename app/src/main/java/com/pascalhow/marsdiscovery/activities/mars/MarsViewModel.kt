package com.pascalhow.planetmars.activities.mars

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.pascalhow.planetmars.data.model.MarsFootage
import com.pascalhow.planetmars.data.repo.MarsRepository

class MarsViewModel(repository: MarsRepository) : ViewModel() {

    private val marsFootageList = repository.getFootage(MARS, MEDIA_TYPE)

    fun getMarsFootageList(): LiveData<List<MarsFootage>> = marsFootageList

    companion object {
        private const val MARS = "mars"
        private const val MEDIA_TYPE = "image"
    }
}

class MarsViewModelFactory(
    private val repository: MarsRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MarsViewModel(repository) as T
    }
}
