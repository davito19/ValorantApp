package com.davito.valorantapp.ui.mymainagents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davito.valorantapp.R
import com.davito.valorantapp.databinding.CardViewAgentItemBinding
import com.davito.valorantapp.local.model.FavoriteAgent
import com.squareup.picasso.Picasso

class FavoriteAgentsListAdapter(
    private val agentsList: ArrayList<FavoriteAgent>,
    private val onItemClick: (FavoriteAgent) -> Unit,
) : RecyclerView.Adapter<FavoriteAgentsListAdapter.AgentsHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentsHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_agent_item, parent, false)
        return AgentsHolder(itemView)
    }

    override fun getItemCount(): Int = agentsList.size

    override fun onBindViewHolder(holder: AgentsHolder, position: Int) {
        val agent = agentsList[position]
        holder.bindAgent(agent)
        holder.itemView.setOnClickListener { onItemClick(agent) }
    }

    fun appendItems(newList: ArrayList<FavoriteAgent>) {
        agentsList.clear()
        agentsList.addAll(newList)
        notifyDataSetChanged()
    }

    class AgentsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewAgentItemBinding.bind(itemView)

        fun bindAgent(agent: FavoriteAgent) {
            with(binding) {
                nameCardTextView.text = agent.name
                developNameCardTextView.text = buildString {
                    append("Develop name: ")
                    append(agent.developerName)
                }
                roleCardTextView.text = buildString {
                    append("Role: ")
                    append(agent.role)
                }
                Picasso.get().load(agent.urlImage).into(agentCardImageView)
            }
        }
    }

}