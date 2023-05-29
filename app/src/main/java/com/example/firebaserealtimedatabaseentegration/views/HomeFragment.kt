package com.example.firebaserealtimedatabaseentegration.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.firebaserealtimedatabaseentegration.MainActivity
import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentHomeBinding
import com.example.firebaserealtimedatabaseentegration.viewmodels.HomeViewModel
import com.example.firebaserealtimedatabaseentegration.views.adapters.HomeProductAdapter


class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    lateinit var adapter: HomeProductAdapter
    private lateinit var binding: FragmentHomeBinding
    private lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel.fetchObjects()

        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            productList?.let {
                adapter = HomeProductAdapter(it, ::onClickAddToCart, ::onClickProductDetail)
                binding.rcyProductList.adapter = adapter
            }
        }


//        viewModel.productList.observeForever {
//            it?.let {
//                adapter = HomeProductAdapter(it, ::onClickAddToCart, ::onClickProductDetail)
//                binding.rcyProductList.adapter = adapter
//            }
//        }
        return binding.root
    }


    private fun onClickAddToCart(product: Product) {

        viewModel.addToCart(product.productID)

        val action = HomeFragmentDirections.actionHomeFragmentToBasketFragment()
        findNavController().navigate(action)
    }

    private fun onClickProductDetail(product: Product) {

        val action = HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(product.productID)
        findNavController().navigate(action)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.basket.setOnClickListener {


            val action = HomeFragmentDirections.actionHomeFragmentToBasketFragment()
            findNavController().navigate(action)

        }
        super.onViewCreated(view, savedInstanceState)
    }
}