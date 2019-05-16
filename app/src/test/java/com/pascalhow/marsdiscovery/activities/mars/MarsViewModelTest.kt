package com.pascalhow.marsdiscovery.activities.mars

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.pascalhow.marsdiscovery.data.repo.MarsRepository
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MarsViewModelTest {

    private lateinit var marsViewModel: MarsViewModel
    private val marsFootageListLiveData = MutableLiveData<List<MarsFootage>>()

    private val footages = listOf(
        MarsFootage("url_one", "description_one", "date_one", 0),
        MarsFootage("url_two", "description_two", "date_two", 1)
    )

    @Mock
    private lateinit var repo: MarsRepository

    @Mock
    private lateinit var observer: Observer<List<MarsFootage>>

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        whenever(repo.getFootage(MARS, MEDIA_TYPE)).thenReturn(marsFootageListLiveData)
        marsViewModel = MarsViewModel(repo)
    }

    @Test
    fun getMarsFootageList() {
        marsViewModel.getMarsFootageList().observeForever(observer)
        verify(repo).getFootage(MARS, MEDIA_TYPE)
    }

    companion object {
        private const val MARS = "mars"
        private const val MEDIA_TYPE = "image"
    }
}
