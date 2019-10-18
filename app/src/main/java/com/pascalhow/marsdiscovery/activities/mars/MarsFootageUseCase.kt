package com.pascalhow.marsdiscovery.activities.mars

import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.pascalhow.marsdiscovery.data.repo.MarsRepository
import com.pascalhow.marsdiscovery.domain.UseCase
import com.pascalhow.marsdiscovery.domain.UseCaseFlowable
import com.pascalhow.marsdiscovery.utils.SchedulersProvider
import io.reactivex.Flowable

class MarsFootageUseCase (
    private val repository: MarsRepository
) : UseCase<List<MarsFootage>, UseCase.None>() {

    override suspend fun run(params: None): List<MarsFootage> {
        return repository.getFootage(MARS, MEDIA_TYPE)
    }

    companion object {
        private const val MARS = "mars"
        private const val MEDIA_TYPE = "image"
    }
}
