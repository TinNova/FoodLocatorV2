package tin.novakovic.foodlocator.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import tin.novakovic.foodlocator.R

class DetailFragment : Fragment() {

    private var args: DetailFragmentArgs? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args = arguments?.let { DetailFragmentArgs.fromBundle(it) }
        println(args?.restaurant?.foodType)
        println(args?.restaurant?.name)
        println(args?.restaurant?.rating)
    }
}