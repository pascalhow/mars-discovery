package com.pascalhow.marsdiscovery.domain

import com.pascalhow.marsdiscovery.utils.SchedulersProvider
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.DisposableSubscriber

abstract class UseCaseFlowable<T, in Params>(
    private val schedulersProvider: SchedulersProvider
) {

    private val disposables = CompositeDisposable()

    abstract fun buildUseCaseFlowable(params: Params? = null): Flowable<T>

    fun execute(subscriber: DisposableSubscriber<T>, params: Params? = null) {
        val disposable = buildUseCaseFlowable(params)
            .subscribeOn(schedulersProvider.io())
            .observeOn(schedulersProvider.mainThread())

        disposables.add(disposable.subscribeWith(subscriber))
    }

    fun dispose() {
        if (!disposables.isDisposed) {
            disposables.dispose()
        }
    }
}
