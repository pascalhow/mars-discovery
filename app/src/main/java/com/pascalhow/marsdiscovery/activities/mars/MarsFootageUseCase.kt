package com.pascalhow.marsdiscovery.activities.mars

import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.pascalhow.marsdiscovery.data.repo.MarsRepository
import com.pascalhow.marsdiscovery.domain.UseCaseFlowable
import com.pascalhow.marsdiscovery.utils.SchedulersProvider
import io.reactivex.Flowable

class MarsFootageUseCase (
    private val repository: MarsRepository,
    schedulersProvider: SchedulersProvider
) : UseCaseFlowable<List<MarsFootage>, Void?>(schedulersProvider) {

    override fun buildUseCaseFlowable(params: Void?): Flowable<List<MarsFootage>> {
        return repository.getFootage(MARS, MEDIA_TYPE)
    }

    companion object {
        private const val MARS = "mars"
        private const val MEDIA_TYPE = "image"
    }
}
