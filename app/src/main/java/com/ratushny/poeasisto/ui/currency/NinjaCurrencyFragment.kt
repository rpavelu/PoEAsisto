package com.ratushny.poeasisto.ui.currency

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.ratushny.poeasisto.R
import com.ratushny.poeasisto.data.ninja.NinjaCurrencyListAdapter
import com.ratushny.poeasisto.data.ninja.NinjaCurrencyListRepositoryImpl
import com.ratushny.poeasisto.data.ninja.NinjaNetworkConverterImpl
import com.ratushny.poeasisto.databinding.NinjaCurrencyFragmentBinding

@Suppress("UNCHECKED_CAST")
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

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}
