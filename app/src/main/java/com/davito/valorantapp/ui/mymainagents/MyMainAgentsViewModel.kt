package com.davito.valorantapp.ui.mymainagents

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davito.valorantapp.local.model.FavoriteAgent
import com.davito.valorantapp.local.repository.FavoriteAgentRepository
import kotlinx.coroutines.launch

class MyMainAgentsViewModel : ViewModel() {

    private val repository = FavoriteAgentRepository()

    private val _agentsLoad : MutableLiveData<ArrayList<FavoriteAgent>> = MutableLiveData()
    val agentsLoad : LiveData<ArrayList<FavoriteAgent>> = _agentsLoad

    fun loadAgents() {
        viewModelScope.launch{
            val agents = repository.loadAgents()
            _agentsLoad.postValue(agents as ArrayList<FavoriteAgent>)
        }
    }


}