package tin.novakovic.foodlocator.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import tin.novakovic.foodlocator.R
import tin.novakovic.foodlocator.common.view_binding.viewBinding
import tin.novakovic.foodlocator.databinding.FragmentDetailBinding

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var args: DetailFragmentArgs? = null
    private val binding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        args = arguments?.let { DetailFragmentArgs.fromBundle(it) }
        println(args?.restaurant?.foodType)
        println(args?.restaurant?.name)
        println(args?.restaurant?.rating)
    }
}