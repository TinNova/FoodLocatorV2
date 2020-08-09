package tin.novakovic.foodlocator.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import tin.novakovic.foodlocator.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory<MainViewModel>
    private lateinit var viewModel: MainViewModel

    private val adapter = MainAdapter()


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
                    }
                    is Error -> {
                        rv_main_activity.gone()
                        loading_icon_main_activity.gone()
                        network_main_activity.visible()
                        network_main_activity.text = it.message
                    }
                    is Loading -> {
                        rv_main_activity.gone()
                        loading_icon_main_activity.visible()
                        network_main_activity.gone()
                    }
                }
            }
        })
    }

    // Setup GPS
    // --> https://developer.android.com/training/location
    // --> https://developer.android.com/reference/android/location/LocationManager

    // Write Tests
    // Look at the OkHttp Testing
    // Remove all hardcoded text in ViewModel and xmls
}
