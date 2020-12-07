package com.example.mypokedex.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mypokedex.R
import com.example.mypokedex.core.data.Resource
import com.example.mypokedex.core.domain.model.Pokemon
import com.example.mypokedex.core.ui.PokemonAdapter
import com.example.mypokedex.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModel()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)

        val searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.query.value = query
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) viewModel.query.value = ""
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun initView() {
        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            adapter = PokemonAdapter(this@HomeFragment::onClickEvent)
            vm = viewModel.apply {
                query.observe(viewLifecycleOwner, {
                    fetchPokemonList(it).observe(viewLifecycleOwner, { resource ->
                        when (resource) {
                            is Resource.Loading -> viewModel.isLoading.value = true
                            is Resource.Success -> {
                                viewModel.isLoading.value = false
                                viewModel.listPokemon.value = resource.data
                            }
                            is Resource.Error -> {
                                viewModel.isLoading.value = false
                                viewModel.errorMessage.value = resource.message
                            }
                        }
                    })
                })
            }
        }
    }

    private fun onClickEvent(pokemon: Pokemon) {
        findNavController().navigate(HomeFragmentDirections.actionHomeDestToDetailDest(pokemon))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}