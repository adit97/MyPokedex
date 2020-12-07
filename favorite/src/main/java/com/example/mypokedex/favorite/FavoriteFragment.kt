package com.example.mypokedex.favorite

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mypokedex.core.domain.model.Pokemon
import com.example.mypokedex.core.ui.PokemonAdapter
import com.example.mypokedex.favorite.databinding.FragmentFavoriteBinding
import com.example.mypokedex.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModel()
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loadKoinModules(favoriteModule)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            adapter = PokemonAdapter(this@FavoriteFragment::onClickEvent)
            vm = viewModel
        }
    }

    private fun onClickEvent(pokemon: Pokemon) {
        findNavController().navigate(
            FavoriteFragmentDirections.actionFavoriteDestToDetailDest(pokemon)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}