package com.example.firebaserealtimedatabaseentegration.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.firebaserealtimedatabaseentegration.R
import com.example.firebaserealtimedatabaseentegration.data.ImagesModel
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentHomeBinding
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentProductDetailBinding
import com.example.firebaserealtimedatabaseentegration.views.adapters.HomeProductAdapter
import com.example.firebaserealtimedatabaseentegration.views.adapters.ImageSlideAdapter
import me.relex.circleindicator.CircleIndicator

class ProductDetailFragment : Fragment() {

    private var imagesModel: ImagesModel? = null
    lateinit var viewPagerAdapter: ImageSlideAdapter
    lateinit var indicator: CircleIndicator
    private lateinit var binding: FragmentProductDetailBinding
    private val args by navArgs<ProductDetailFragmentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(layoutInflater)


        args.productId?.let {
            binding.productID = it
        }

        val mockImages = arrayListOf(
            "https://cdn.dsmcdn.com/mnresize/1200/1800/ty890/product/media/images/20230517/16/351229476/873161061/2/2_org_zoom.jpg",
            "https://cdn.dsmcdn.com/mnresize/1200/1800/ty888/product/media/images/20230517/22/351433449/889023343/2/2_org_zoom.jpg",
        )


        imagesModel = ImagesModel(mockImages)



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imagesModel?.images?.let {
            viewPagerAdapter = ImageSlideAdapter(requireContext(), it)
            binding.viewpager.adapter = viewPagerAdapter
            indicator = requireView().findViewById(R.id.indicator) as CircleIndicator
            indicator.setViewPager(binding.viewpager)
        }

    }
}