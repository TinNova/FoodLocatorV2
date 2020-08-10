package tin.novakovic.foodlocator.ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_main.*
import tin.novakovic.foodlocator.*
import tin.novakovic.foodlocator.R
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var viewModel: MainViewModel

    private val adapter = MainAdapter()

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback


    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

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
        rv_main_activity.adapter = adapter
        rv_main_activity.layoutManager = LinearLayoutManager(
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
            it?.let {
                when (it) {
                    is Presenting -> {
                        adapter.setData(it.restaurant)
                        rv_main_activity.visible()
                        loading_icon_main_activity.gone()
                        network_main_activity.gone()
                        location_button.gone()
                        terminateLocationSubscription()
                    }
                    is Error -> {
                        rv_main_activity.gone()
                        loading_icon_main_activity.gone()
                        network_main_activity.visible()
                        network_main_activity.text = getString(it.message)
                        location_button.gone()
                    }
                    is Loading -> {
                        rv_main_activity.gone()
                        loading_icon_main_activity.visible()
                        network_main_activity.gone()
                        location_button.gone()
                    }
                    is LocationPermitted -> {
                        fetchUserLocation()
                    }
                    is LocationNotPermitted -> {
                        requestLocationPermissions()
                    }
                }
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
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
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
