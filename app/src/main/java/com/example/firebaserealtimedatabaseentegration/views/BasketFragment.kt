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
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentBasketBinding
import com.example.firebaserealtimedatabaseentegration.viewmodels.BasketViewModel
import com.example.firebaserealtimedatabaseentegration.views.adapters.BasketProductAdapter

class BasketFragment : Fragment() {

    private val viewModel: BasketViewModel by viewModels()


    lateinit var adapter: BasketProductAdapter
    private lateinit var binding: FragmentBasketBinding
    private lateinit var mainActivity: MainActivity

    //    private val args by navArgs<BasketFragmentArgs>()
//    val viewModel = ViewModelProvider(this).get(BasketViewModel::class.java)


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBasketBinding.inflate(layoutInflater)

//        args.productList?.let {
//            Log.d("HOBAA", it.size.toString())
//        }

//        viewModel.productList.observe() {
//            adapter = BasketProductAdapter(it, ::onClickProductDetail)
//            binding.rcyBasketProductList.adapter = adapter
//        }

        viewModel.fetchObjects()

//        viewModel.productList.observe(viewLifecycleOwner, Observer { list ->
//            Log.d("LIVEDATA", list.size.toString())
//
//            adapter = BasketProductAdapter(list, ::onClickProductDetail)
//            binding.rcyBasketProductList.adapter = adapter
//
//        })

        viewModel.productList.observe(viewLifecycleOwner) { productList ->

            Log.d("LIVEDATA", productList.size.toString())
            adapter = BasketProductAdapter(productList, ::onClickProductDetail)
            binding.rcyBasketProductList.adapter = adapter
        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun onClickProductDetail(product: Product) {

        val action =
            HomeFragmentDirections.actionHomeFragmentToProductDetailFragment(product.productID)
        findNavController().navigate(action)


    }

}