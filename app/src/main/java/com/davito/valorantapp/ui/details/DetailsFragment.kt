package com.davito.valorantapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.davito.valorantapp.R
import com.davito.valorantapp.databinding.FragmentDetailsBinding
import com.davito.valorantapp.server.model.Agent
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private var favorite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        val agent: Agent = args.agent

        viewModel.searchAgent(agent.uuid)

        viewModel.isAgentFavorite.observe(viewLifecycleOwner) {
            favorite = it
            if (favorite)
                binding.favoriteDetailsImageView.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.favorite_24, null))
            else
                binding.favoriteDetailsImageView.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.favorite_border_24, null))
        }

        viewModel.isDeleteOrSave.observe(viewLifecycleOwner) {
            viewModel.searchAgent(agent.uuid)
        }

        with(binding) {
            nameAgentDetailsTextView.text = agent.displayName
            developerNameDetailsTextView.text = agent.developerName
            descriptionAgentDetailsTextView.text = agent.description
            roleDetailsTextview.text = agent.role?.displayName
            roleDescriptionDetailsTextView.text = agent.role?.description

            Picasso.get().load(agent.fullPortrait).into(agentDetailsImageView)

            favoriteDetailsImageView.setOnClickListener {
                if (favorite) {
                    viewModel.deleteAgent(agent)
                } else {
                    viewModel.saveAgent(agent)
                }
            }
        }



        return binding.root
    }


}