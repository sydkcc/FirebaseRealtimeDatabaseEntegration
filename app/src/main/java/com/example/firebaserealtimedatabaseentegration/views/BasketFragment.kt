package com.example.firebaserealtimedatabaseentegration.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.firebasedbentegration.extensions.addTL
import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentBasketBinding
import com.example.firebaserealtimedatabaseentegration.extensions.addProductString
import com.example.firebaserealtimedatabaseentegration.viewmodels.BasketViewModel
import com.example.firebaserealtimedatabaseentegration.views.adapters.BasketProductAdapter

class BasketFragment : Fragment() {

    private val viewModel: BasketViewModel by viewModels()
    lateinit var adapter: BasketProductAdapter
    private lateinit var binding: FragmentBasketBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(layoutInflater)
        viewModel.fetchObjects()
        viewModel.productList.observe(viewLifecycleOwner) { productList ->

            var filteredList = productList.filter { it.isInsideBasket == "1" }
            Log.d("LIVEDATA", filteredList.size.toString())



            if (filteredList.isNotEmpty()) {
                binding.price = filteredList.map { it.productPrice }.sum().addTL()
                binding.count = filteredList.size.addProductString()
                adapter = BasketProductAdapter(::onClickProductDetail)
                adapter.setData(filteredList)
                binding.rcyBasketProductList.adapter = adapter
            } else {
                binding.rcyBasketProductList.visibility = View.GONE
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            val action = BasketFragmentDirections.actionBasketFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        binding.clearBasket.setOnClickListener {
            binding.rcyBasketProductList.visibility = View.GONE
            binding.priceContainer.visibility = View.GONE
            adapter.clearData()
            viewModel.clearBasket()
        }
    }

    private fun onClickProductDetail(product: Product) {
        val action =
            BasketFragmentDirections.actionBasketFragmentToProductDetailFragment(product.productID)
        findNavController().navigate(action)
    }
}