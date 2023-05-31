package com.davito.valorantapp.ui.agents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.davito.valorantapp.databinding.FragmentAgentsBinding
import com.davito.valorantapp.server.model.Agent

class AgentsFragment : Fragment() {

    private lateinit var binding: FragmentAgentsBinding
    private lateinit var viewModel: AgentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AgentsViewModel::class.java]
        binding = FragmentAgentsBinding.inflate(inflater, container, false)

        val agentList = ArrayList<Agent>()
        val agentsListAdapter = AgentsListAdapter(agentList, onItemClick = {onItemClick(it)})

        binding.agentRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@AgentsFragment.requireContext())
            adapter = agentsListAdapter
            setHasFixedSize(false)
        }

        viewModel.loadAgents()

        viewModel.agentsLoad.observe(viewLifecycleOwner){
            agentsListAdapter.appendItems(it)
        }

        return binding.root
    }

    private fun onItemClick(agent: Agent) {
        findNavController().navigate(AgentsFragmentDirections.actionNavigationAgentsToDetailsFragment(agent = agent))
    }

}