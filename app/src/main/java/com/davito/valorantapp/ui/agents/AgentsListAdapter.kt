package com.davito.valorantapp.ui.agents

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davito.valorantapp.R
import com.davito.valorantapp.databinding.CardViewAgentItemBinding
import com.davito.valorantapp.server.model.Agent
import com.squareup.picasso.Picasso

class AgentsListAdapter(
    private val agentsList: ArrayList<Agent>,
    private val onItemClick: (Agent) -> Unit,
) : RecyclerView.Adapter<AgentsListAdapter.AgentsHolder>() {


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

    fun appendItems(newList: ArrayList<Agent>) {
        agentsList.clear()
        agentsList.addAll(newList)
        notifyItemInserted(newList.size)
    }

    class AgentsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardViewAgentItemBinding.bind(itemView)

        fun bindAgent(agent: Agent) {
            with(binding) {
                    nameCardTextView.text = agent.displayName
                    developNameCardTextView.text = buildString {
                        append("Develop name: ")
                        append(agent.developerName)
                    }
                    roleCardTextView.text = buildString {
                        append("Role: ")
                        append(agent.role?.displayName)
                    }
                    Picasso.get().load(agent.fullPortrait).into(agentCardImageView)
            }
        }
    }

}