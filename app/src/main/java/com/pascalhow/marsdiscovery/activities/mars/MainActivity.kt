package com.pascalhow.marsdiscovery.activities.mars

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.View
import com.pascalhow.marsdiscovery.R
import com.pascalhow.marsdiscovery.data.model.MarsFootage
import com.pascalhow.marsdiscovery.data.repo.MarsDataStoreFactory
import com.pascalhow.marsdiscovery.data.repo.MarsRepository
import com.pascalhow.marsdiscovery.data.resource.ResourceState
import com.pascalhow.marsdiscovery.utils.NetworkStatusProvider
import com.pascalhow.marsdiscovery.utils.SchedulersProvider
import kotlinx.android.synthetic.main.activity_main.mars_footage_recycler_view as recyclerView
import kotlinx.android.synthetic.main.activity_main.progress_bar as progressBar
import kotlinx.android.synthetic.main.activity_main.sad_icon as sadIcon

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MarsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar.indeterminateDrawable.setColorFilter(
            ContextCompat.getColor(this, R.color.colorPrimary)
            , PorterDuff.Mode.SRC_IN
        )

        val networkStatusProvider = NetworkStatusProvider(applicationContext)
        val repository = MarsRepository(
            MarsDataStoreFactory(
                applicationContext,
                networkStatusProvider
            )
        )

        val marsFootageUseCase = MarsFootageUseCase(repository)
        val viewModelFactory = MarsViewModelFactory(marsFootageUseCase)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MarsViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        viewModel.getMarsFootageListLiveData().observe(this, Observer { marsFootageList ->
            marsFootageList?.let {
                handleDataState(it.status, it.data, it.message)
            }
        })
    }

    private fun handleDataState(resourceState: ResourceState, data: List<MarsFootage>?, message: String?) {
        when (resourceState) {
            ResourceState.Success -> setUpScreenForSuccess(data)
            ResourceState.Loading -> setUpScreenForLoading()
            ResourceState.Error -> setUpScreenForError(message)
        }
    }

    private fun setUpScreenForSuccess(data: List<MarsFootage>?) {
        progressBar.visibility = View.GONE

        if (data != null && data.isNotEmpty()) {
            sadIcon.visibility = View.GONE
            populateMarsFootageList(data)
            recyclerView.visibility = View.VISIBLE
        } else {
            recyclerView.visibility = View.GONE
            sadIcon.visibility = View.VISIBLE
        }
    }

    private fun setUpScreenForLoading() {
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
        sadIcon.visibility = View.GONE
    }

    private fun setUpScreenForError(message: String?) {
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.GONE
        sadIcon.visibility = View.VISIBLE
        Log.e("Error loading data", message)
    }

    override fun onDestroy() {
        viewModel.dispose()
        super.onDestroy()
    }

    private fun populateMarsFootageList(list: List<MarsFootage>) {
        recyclerView.adapter = MarsFootageAdapter(list)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
    }
}
