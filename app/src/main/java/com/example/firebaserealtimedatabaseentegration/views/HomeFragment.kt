package com.example.firebaserealtimedatabaseentegration.views

import android.R
import android.content.Context
import android.database.DatabaseUtils
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.firebaserealtimedatabaseentegration.MainActivity
import com.example.firebaserealtimedatabaseentegration.databinding.FragmentHomeBinding
import com.example.firebaserealtimedatabaseentegration.viewmodels.HomeViewModel
import com.example.firebaserealtimedatabaseentegration.views.adapters.HomeProductAdapter
import com.google.firebase.database.annotations.Nullable


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


        viewModel.productList.observeForever {
            Log.d("DamacanaDenem", it.size.toString())
            it?.let {
                adapter = HomeProductAdapter(it)
                binding.rcyProductList.adapter = adapter


            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.basket.setOnClickListener {



            val action = HomeFragmentDirections.actionHomeFragmentToBasketFragment()
            findNavController().navigate(action)




//            val bundle = Bundle().apply {
//                putString("myData", "Hello World")
//            }
//            val action = HomeFragmentDirections.actionHomeFragmentToBasketFragment().setArguments(bundle)
//            findNavController().navigate(action)

//            requireActivity().findNavController(R.id.nav_host_fragment).navigate(R.id.action_homeFragment_to_basketFragment)

//            mainActivity.navController.navigate(R.id.action_homeFragment_to_basketFragment, args)


//            findNavController().navigate(R.id.action_homeFragment_to_basketFragment, args)

        }

        binding.logo.setOnClickListener {


            viewModel.addToCart(5)
        }
        super.onViewCreated(view, savedInstanceState)
    }
}