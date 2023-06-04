package com.example.firebaserealtimedatabaseentegration.presenters.presenter.views

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentHomeBinding
import com.example.firebaserealtimedatabaseentegration.presenters.viewmodel.HomeViewModel
import com.example.firebaserealtimedatabaseentegration.presenters.presenter.views.adapters.HomeProductAdapter
import java.util.Locale


class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
    lateinit var adapter: HomeProductAdapter
    private lateinit var binding: FragmentHomeBinding
    private var listOfProduct = listOf<Product>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        viewModel.fetchObjects()

        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            productList?.let {
                listOfProduct = it
                adapter = HomeProductAdapter(::onClickAddToCart, ::onClickProductDetail)
                adapter.setData(it)
                binding.rcyProductList.adapter = adapter
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.basket.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToBasketFragment()
            findNavController().navigate(action)
        }

        binding.search.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            }
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }
            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })
    }

    private fun filter(text: String) {
        val filteredList = ArrayList(listOfProduct.filter { products ->
            products.productName.lowercase(Locale.ROOT).contains(text.lowercase(Locale.ROOT))
        })

        if (filteredList.isNotEmpty()) {
            adapter.setData(filteredList)
        }
    }

    private fun onClickAddToCart(product: Product) {
        viewModel.addToBasketAction(product.productID)
        val action = HomeFragmentDirections.actionHomeFragmentToBasketFragment()
        findNavController().navigate(action)
    }

    private fun onClickProductDetail(product: Product) {
        val action =
            HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(product.productID)
        findNavController().navigate(action)
    }
}