package com.example.mypokedex.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.mypokedex.core.data.Resource
import com.example.mypokedex.databinding.FragmentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModel()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private fun initView() {
        val safeArgs: DetailFragmentArgs by navArgs()
        val pokemon = safeArgs.pokemon
        viewModel.pokemon.value = pokemon
        viewModel.isFavoritePokemon.value = pokemon.pokemonIsFavorite

        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel.apply {
                viewModel.pokemonDetail()?.observe(viewLifecycleOwner, { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            viewModel.isLoading.value = true
                        }
                        is Resource.Success -> {
                            viewModel.isLoading.value = false
                            viewModel.pokemonDetail.value = resource.data
                        }
                        is Resource.Error -> {
                            viewModel.isLoading.value = false
                            viewModel.errorMessage.value = resource.message
                        }
                    }
                })
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
