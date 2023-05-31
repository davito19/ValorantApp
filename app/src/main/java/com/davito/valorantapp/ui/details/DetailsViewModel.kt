package com.davito.valorantapp.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davito.valorantapp.local.model.FavoriteAgent
import com.davito.valorantapp.local.repository.FavoriteAgentRepository
import com.davito.valorantapp.server.model.Agent
import kotlinx.coroutines.launch

class DetailsViewModel : ViewModel() {

    private val repository = FavoriteAgentRepository()

    private val _isAgentfavorite : MutableLiveData<Boolean> = MutableLiveData()
    val isAgentFavorite : LiveData<Boolean> = _isAgentfavorite

    private val _isDeleteOrSave : MutableLiveData<Boolean> = MutableLiveData()
    val isDeleteOrSave : LiveData<Boolean> = _isDeleteOrSave

    fun searchAgent(uuid: String) {
        var agentFavorite = false
        viewModelScope.launch {
            val favoriteAgent = repository.searchAgent(uuid)
            if (favoriteAgent != null) agentFavorite = true
            _isAgentfavorite.postValue(agentFavorite)
        }
    }

    fun deleteAgent(agent: Agent) {
        viewModelScope.launch {
            repository.deleteAgets(agent.uuid)
            _isDeleteOrSave.postValue(true)
        }
    }

    fun saveAgent(agent: Agent) {
        val favoriteAgent = FavoriteAgent(
            id = agent.uuid,
            name = agent.displayName,
            developerName = agent.developerName,
            description = agent.description,
            role = agent.role?.displayName,
            roleDescription = agent.role?.description,
            urlImage = agent.fullPortrait
        )
        viewModelScope.launch {
            repository.saveAgent(favoriteAgent)
            _isDeleteOrSave.postValue(true)
        }
    }
}