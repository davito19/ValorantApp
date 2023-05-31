package com.davito.valorantapp.ui.favoritedeails

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.fragment.navArgs
import com.davito.valorantapp.R
import com.davito.valorantapp.databinding.FragmentFavoriteDetailsBinding
import com.davito.valorantapp.local.model.FavoriteAgent
import com.squareup.picasso.Picasso

class FavoriteDetailsFragment : Fragment() {



    private lateinit var viewModel: FavoriteDetailsViewModel
    private lateinit var binding: FragmentFavoriteDetailsBinding
    private val args: FavoriteDetailsFragmentArgs by navArgs()
    private var favorite = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteDetailsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[FavoriteDetailsViewModel::class.java]

        val agent : FavoriteAgent =  args.favoriteAgent

        viewModel.searchAgent(agent.id)

        viewModel.isAgentFavorite.observe(viewLifecycleOwner) {
            favorite = it
            if (favorite)
                binding.favoriteDetailsImageView.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.favorite_24, null))
            else
                binding.favoriteDetailsImageView.setImageDrawable(ResourcesCompat.getDrawable(resources, R.drawable.favorite_border_24, null))
        }

        viewModel.isDeleteOrSave.observe(viewLifecycleOwner) {
            viewModel.searchAgent(agent.id)
        }


        with(binding) {
            nameAgentDetailsTextView.text = agent.name
            developerNameDetailsTextView.text = agent.developerName
            descriptionAgentDetailsTextView.text = agent.description
            roleDetailsTextview.text = agent.role
            roleDescriptionDetailsTextView.text = agent.roleDescription

            Picasso.get().load(agent.urlImage).into(agentDetailsImageView)

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