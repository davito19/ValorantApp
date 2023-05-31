package com.davito.valorantapp.ui.agents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davito.valorantapp.server.model.Agent
import com.davito.valorantapp.server.model.AgentsList
import com.davito.valorantapp.server.repository.AgentsRepository
import kotlinx.coroutines.launch

class AgentsViewModel : ViewModel() {

    private val agentsRepository = AgentsRepository()

    private val _agentsLoad : MutableLiveData<ArrayList<Agent>> = MutableLiveData()
    val agentsLoad : LiveData<ArrayList<Agent>> = _agentsLoad

    fun loadAgents() {
        viewModelScope.launch {
            val agentList : AgentsList = agentsRepository.loadAgents()
            val lista : ArrayList<Agent> = cleanData(agentList.agents)
            _agentsLoad.postValue(lista)
        }
    }

    private fun cleanData(agents: List<Agent>): ArrayList<Agent> {
        return agents.filter { agent : Agent -> agent.role!=null } as ArrayList<Agent>
    }


}