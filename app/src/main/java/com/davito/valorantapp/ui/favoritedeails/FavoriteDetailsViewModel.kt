package com.davito.valorantapp.ui.favoritedeails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davito.valorantapp.local.model.FavoriteAgent
import com.davito.valorantapp.local.repository.FavoriteAgentRepository
import kotlinx.coroutines.launch

class FavoriteDetailsViewModel : ViewModel() {

    private val repository = FavoriteAgentRepository()

    private val _isAgentfavorite : MutableLiveData<Boolean> = MutableLiveData()
    val isAgentFavorite : LiveData<Boolean> = _isAgentfavorite

    private val _isDeleteOrSave : MutableLiveData<Boolean> = MutableLiveData()
    val isDeleteOrSave : LiveData<Boolean> = _isDeleteOrSave

    fun searchAgent(id: String) {
        var agentFavorite = false
        viewModelScope.launch {
            val favoriteAgent = repository.searchAgent(id)
            if (favoriteAgent != null) agentFavorite = true
            _isAgentfavorite.postValue(agentFavorite)
        }
    }

    fun deleteAgent(agent: FavoriteAgent) {
        viewModelScope.launch {
            repository.deleteAgets(agent.id)
            _isDeleteOrSave.postValue(true)
        }
    }

    fun saveAgent(agent: FavoriteAgent) {
        viewModelScope.launch {
            repository.saveAgent(agent)
            _isDeleteOrSave.postValue(true)
        }
    }

}