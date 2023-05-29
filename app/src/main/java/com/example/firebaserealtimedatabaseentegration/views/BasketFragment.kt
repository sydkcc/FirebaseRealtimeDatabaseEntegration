package com.example.firebaserealtimedatabaseentegration.views

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.firebaserealtimedatabaseentegration.MainActivity
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentBasketBinding
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentHomeBinding
import com.example.firebaserealtimedatabaseentegration.viewmodels.BasketViewModel
import com.example.firebaserealtimedatabaseentegration.viewmodels.HomeViewModel
import com.example.firebaserealtimedatabaseentegration.views.adapters.HomeProductAdapter

class BasketFragment :Fragment(){

    private val viewModel: BasketViewModel by viewModels()
    lateinit var adapter: HomeProductAdapter
    private lateinit var binding: FragmentBasketBinding
    private lateinit var mainActivity: MainActivity
//    private val args by navArgs<BasketFragmentArgs>()


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
        return binding.root
    }


}