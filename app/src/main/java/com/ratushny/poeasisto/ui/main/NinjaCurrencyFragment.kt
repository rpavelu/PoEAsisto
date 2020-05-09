package com.ratushny.poeasisto.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ratushny.poeasisto.data.ninja.NinjaCurrencyListAdapter
import com.ratushny.poeasisto.data.ninja.NinjaCurrencyListRepositoryImpl
import com.ratushny.poeasisto.data.ninja.NinjaNetworkConverterImpl
import com.ratushny.poeasisto.databinding.NinjaCurrencyFragmentBinding

class NinjaCurrencyFragment : Fragment() {

    private lateinit var viewModel: NinjaCurrencyViewModel
    private lateinit var binding: NinjaCurrencyFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = NinjaCurrencyFragmentBinding.inflate(
            inflater,
            container,
            false
        )

        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return NinjaCurrencyViewModel(
                    NinjaCurrencyListRepositoryImpl(
                        NinjaNetworkConverterImpl()
                    )
                ).also {
                    it.getData()
                } as T
            }
        }).get(NinjaCurrencyViewModel::class.java)

        binding.mainViewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = NinjaCurrencyListAdapter()
        binding.listScreenRecyclerview.adapter = adapter

        viewModel.ninjaCurrencyList.observe(viewLifecycleOwner, Observer {
            adapter.addCurrencyList(it)
        })

        return binding.root
    }
}
