package com.davito.valorantapp.ui.mymainagents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davito.valorantapp.databinding.FragmentMyMainAgentsBinding
import com.davito.valorantapp.local.model.FavoriteAgent


class MyMainAgentsFragment : Fragment() {

    private lateinit var binding: FragmentMyMainAgentsBinding
    private lateinit var viewModel: MyMainAgentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[MyMainAgentsViewModel::class.java]
        binding = FragmentMyMainAgentsBinding.inflate(inflater, container, false)

        val favoritesAgents = ArrayList<FavoriteAgent>()
        val agentsAdapter = FavoriteAgentsListAdapter(
            favoritesAgents,
            onItemClick = {onItemClicked(it)}
        )

        binding.agentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MyMainAgentsFragment.requireContext())
            adapter = agentsAdapter
            setHasFixedSize(false)
        }

        viewModel.loadAgents()

        viewModel.agentsLoad.observe(viewLifecycleOwner){
            agentsAdapter.appendItems(it)
        }

        return binding.root
    }

    private fun onItemClicked(agent: FavoriteAgent) {
        findNavController().navigate(MyMainAgentsFragmentDirections.actionNavigationMyMainAgentsToFavoriteDetailsFragment(agent))
    }


}