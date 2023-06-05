package com.example.firebaserealtimedatabaseentegration.presenters.presenter.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.firebasedbentegration.extensions.format
import com.example.firebaserealtimedatabaseentegration.data.Product
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentBasketBinding
import com.example.firebaserealtimedatabaseentegration.extensions.addProductString
import com.example.firebaserealtimedatabaseentegration.extensions.addTL
import com.example.firebaserealtimedatabaseentegration.presenters.viewmodel.BasketViewModel
import com.example.firebaserealtimedatabaseentegration.presenters.presenter.views.adapters.BasketProductAdapter

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

            val filteredList = productList.filter { it.isInsideBasket == INSIDE_BASKET }

            if (filteredList.isNotEmpty()) {
                binding.totalPrice = filteredList.sumOf { it.productPrice }.format(2).addTL()

                binding.count =
                    filteredList.filter { it.isInsideBasket == BasketFragment.INSIDE_BASKET }.size.addProductString()
                adapter = BasketProductAdapter(::onClickProductDetail, ::onClickProductDelete)
                adapter.setData(filteredList)
                binding.rcyBasketProductList.adapter = adapter
            } else {
                binding.rcyBasketProductList.visibility = View.GONE
                binding.priceContainer.visibility = View.GONE
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.clearBasket.setOnClickListener {
            clearBasket()
        }
    }

    private fun onClickProductDetail(product: Product) {
        val action =
            BasketFragmentDirections.actionBasketFragmentToProductDetailFragment(product.productID)
        findNavController().navigate(action)
    }

    private fun onClickProductDelete(product: Product) {
//        viewModel.deleteFromCart(product.productID)
        viewModel.deleteProductAction(product.productID)
        adapter.items.remove(product)
        binding.totalPrice = adapter.items.sumOf { it.productPrice }.format(2).addTL()
        binding.count = adapter.items.size.addProductString()
        adapter.notifyDataSetChanged()
        if (adapter.items.isEmpty()) {
            clearBasket()
        }
    }

    private fun clearBasket() {
        binding.rcyBasketProductList.visibility = View.GONE
        binding.priceContainer.visibility = View.GONE
        adapter.clearData()
        viewModel.clearBasketAction(10)
        binding.count = adapter.items.size.addProductString()
    }

    companion object {
        const val INSIDE_BASKET = "1"
    }
}