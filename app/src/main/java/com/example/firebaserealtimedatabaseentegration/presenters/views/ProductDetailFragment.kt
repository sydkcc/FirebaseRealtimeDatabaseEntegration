package com.example.firebaserealtimedatabaseentegration.presenters.presenter.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.firebaserealtimedatabaseentegration.R
import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentProductDetailBinding
import com.example.firebaserealtimedatabaseentegration.extensions.safeGet
import com.example.firebaserealtimedatabaseentegration.presenters.viewmodel.ProductDetailViewModel
import com.example.firebaserealtimedatabaseentegration.presenters.presenter.views.adapters.ImageSlideAdapter
import me.relex.circleindicator.CircleIndicator

class ProductDetailFragment : Fragment() {

    private val viewModel: ProductDetailViewModel by viewModels()
    private val images: ArrayList<String> = ArrayList()
    var productId: Int = 0
    var product: Product? = null
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

        args.productId.let { productID ->
            productId = productID.toInt()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            val product = productList[productId]
            this.product = product
            binding.product = product
            images.add(product.productImages.image1)
            images.add(product.productImages.image2)

            images.let {
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

        binding.addToBasket.setOnClickListener {
//            viewModel.addToCart(product?.productID.safeGet())
            viewModel.addToBasketAction(product?.productID.safeGet())
            val action = ProductDetailFragmentDirections.actionProductDFragmentToBasketFragment()
            findNavController().navigate(action)
        }

    }
}