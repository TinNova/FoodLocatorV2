package tin.novakovic.foodlocator.ui

import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_main.*
import tin.novakovic.foodlocator.*
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var viewModel: MainViewModel

    private val adapter = MainAdapter()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

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
            // WE NEED TO CHECK IF APP HAS ACCESS TO LOCATION, IF NOT THEN REQUEST IT, ALSO
            // IF THERE IS NO LASTLOCATION WE NEED TO RETREIVE THE CURRENT LOCATION
            // THE REASON IT WORKED BEFORE IS BECAUSE THE CODELAB APP WAS RETRIEVING THE LOCATION AND OUR APP WAS USING THAT
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    if (location != null) {
                        viewModel.onLocationClicked(location.latitude, location.longitude)
                    } else {
                        viewModel.onLocationError()
                    }
                }
                .addOnFailureListener {
                    it
                }
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
                }
            }
        })
    }

    // Write Tests
    // Look at the OkHttp Testing
}
