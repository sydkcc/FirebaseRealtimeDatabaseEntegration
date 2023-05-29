package com.example.firebaserealtimedatabaseentegration.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.firebaserealtimedatabaseentegration.R
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentProductDetailBinding
import com.example.firebaserealtimedatabaseentegration.viewmodels.ProductDetailViewModel
import com.example.firebaserealtimedatabaseentegration.views.adapters.ImageSlideAdapter
import com.example.firebaserealtimedatabaseentegration.views.dialog.ImageShowDialog
import me.relex.circleindicator.CircleIndicator

class ProductDetailFragment : Fragment() {

    private val viewModel: ProductDetailViewModel by viewModels()

    //    private var imagesModel: ImagesModel? = null
    val images: ArrayList<String> = ArrayList()
    var productId: Int = 0
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

        viewModel.fetchObjects()
        args.productId?.let { productID ->
            binding.productID = productID
            productId = productID.toInt()

        }









        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val mockImages = arrayListOf(
//            "https://cdn.dsmcdn.com/mnresize/1200/1800/ty890/product/media/images/20230517/16/351229476/873161061/2/2_org_zoom.jpg",
//            "https://cdn.dsmcdn.com/mnresize/1200/1800/ty888/product/media/images/20230517/22/351433449/889023343/2/2_org_zoom.jpg",
//        )


        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            Log.d("IMAGESss", productId.toString())


            var product = productList[productId]
            images.add(product.productImages.image1)
            images.add(product.productImages.image2)

            Log.d("IMAGES", images.size.toString())



            images?.let {
                viewPagerAdapter = ImageSlideAdapter(requireContext(), it)
                binding.viewpager.adapter = viewPagerAdapter
                indicator = requireView().findViewById(R.id.indicator) as CircleIndicator
                indicator.setViewPager(binding.viewpager)
            }


        }


        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.basket.setOnClickListener {
            val action = ProductDetailFragmentDirections.actionProductDFragmentToBasketFragment()
            findNavController().navigate(action)
        }


        binding.viewpager.setOnClickListener {
            val dialog = ImageShowDialog.newInstance(images.first())
            dialog.show(requireActivity().supportFragmentManager, ImageShowDialog.TAG)
        }

    }
}