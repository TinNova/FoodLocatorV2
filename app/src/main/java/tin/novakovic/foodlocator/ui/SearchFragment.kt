package tin.novakovic.foodlocator.ui

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_search.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import tin.novakovic.foodlocator.R
import tin.novakovic.foodlocator.domain.Restaurant
import tin.novakovic.foodlocator.show

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()
    private val adapter: SearchAdapter by inject()
    private var mContext: Context? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_search, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        observeViewState()
        recycler_view.adapter = adapter
        adapter.clickListener { onItemClicked(it) }
        recycler_view.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL, false
        )

        search_button.setOnClickListener {
            viewModel.onSearchClicked(search_bar.text.toString())
        }

        location_button.setOnClickListener {
            viewModel.onLocationButtonClicked(locationPermissionApproved())
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context

    }

    private fun observeViewState() {
        viewModel.viewState.observe(this, Observer {

            recycler_view.show(it is SearchViewState.Presenting)
            loading_icon.show(it is SearchViewState.Loading)
            network_error_tv.show(it is SearchViewState.Erroring)
            location_button.show(it !is SearchViewState.Presenting || it is LocationState.LocationNotPermitted)

            when (it) {
                is SearchViewState.Presenting -> adapter.setData(it.restaurant)
                is SearchViewState.Erroring -> network_error_tv.text =
                    resources.getString(it.message)
                is LocationState.LocationNotPermitted -> requestLocationPermissions()
            }
        })
    }

    private fun locationPermissionApproved(): Boolean {
        return PackageManager.PERMISSION_GRANTED == checkSelfPermission(
            mContext!!,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private fun requestLocationPermissions() {
        requestPermissions(
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE -> when {

                grantResults.isEmpty() -> viewModel.onLocationError()
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> viewModel.getLocation()

                else -> {
                    viewModel.onLocationPermissionError()
                }
            }
        }
    }

    private fun onItemClicked(restaurant: Restaurant) {
        println("${restaurant.name} clicked")
    }

    companion object {
        private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
    }

}