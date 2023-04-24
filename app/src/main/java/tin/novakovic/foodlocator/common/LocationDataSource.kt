package tin.novakovic.foodlocator.common

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.os.Looper
import com.google.android.gms.location.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.subjects.PublishSubject
import tin.novakovic.foodlocator.data.LocationModel
import tin.novakovic.foodlocator.data.LocationProvider
import java.util.concurrent.TimeUnit

class LocationDataSource(context: Context) :
    LocationProvider {


    private val locationSubject = PublishSubject.create<LocationModel>()
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest: LocationRequest = LocationRequest.create().apply {
        interval = TimeUnit.SECONDS.toMillis(1)
        //priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        // Use this option whenever uses the emulator, that's the only way to force the emulator
        // GPS to be activated
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult?.locations?.forEach(::setLocation)
        }
    }

    override fun getLocation(): Flowable<LocationModel> = locationSubject.toFlowable(BackpressureStrategy.MISSING)
        .doOnSubscribe {
            startLocationUpdates() }
        .doOnCancel {
            stopLocationUpdates() }

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        fusedLocationClient.lastLocation.addOnSuccessListener(::setLocation)
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
    }

    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    private fun setLocation(location: Location) {
        locationSubject.onNext(
            LocationModel(
                location.latitude,
                location.longitude
            )
        )
    }
}