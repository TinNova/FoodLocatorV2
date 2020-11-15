package tin.novakovic.foodlocator.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import tin.novakovic.foodlocator.*
import tin.novakovic.foodlocator.R
import tin.novakovic.foodlocator.ui.LocationState.*
import tin.novakovic.foodlocator.ui.MainViewState.*
import java.util.concurrent.TimeUnit
import org.koin.android.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()
    private val adapter: MainAdapter by inject()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)

                locationResult?.let {
                    viewModel.onLocationResultSuccess(
                        it.lastLocation.latitude,
                        it.lastLocation.longitude
                    )
                }
            }
        }

        initView()

    }

    private fun initView() {
        observeViewState()
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false
        )

        search_button.setOnClickListener {
            viewModel.onSearchClicked(search_bar.text.toString())
        }

        location_button.setOnClickListener {
            viewModel.onLocationButtonClicked(locationPermissionApproved())
        }
    }


    private fun observeViewState() {
        viewModel.viewState.observe(this, Observer {

            recycler_view.show(it is Presenting)
            loading_icon.show(it is Loading)
            network_error_tv.show(it is Erroring)
            location_button.show(it is LocationPermitted || it is LocationNotPermitted)

            when (it) {
                is Presenting -> {
                    adapter.setData(it.restaurant)
                    terminateLocationSubscription()
                }
                is Erroring -> network_error_tv.text = resources.getString(it.message)
                is LocationPermitted -> fetchUserLocation()
                is LocationNotPermitted -> requestLocationPermissions()
            }
        })
    }

    private fun locationPermissionApproved(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private fun requestLocationPermissions() {
        ActivityCompat.requestPermissions(
            this@MainActivity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE
        )
    }

    private fun fetchUserLocation() {
        locationRequest = LocationRequest().apply {
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
            interval = TimeUnit.SECONDS.toMillis(1)
            numUpdates = 1

        }
        locationRequest.interval

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
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
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> viewModel.onLocationPermissionGranted()

                else -> {
                    viewModel.onLocationPermissionError()
                }
            }
        }
    }

    private fun terminateLocationSubscription() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    companion object {
        private const val REQUEST_FOREGROUND_ONLY_PERMISSIONS_REQUEST_CODE = 34
    }
}
