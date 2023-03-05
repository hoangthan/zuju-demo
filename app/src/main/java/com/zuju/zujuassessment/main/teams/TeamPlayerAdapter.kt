package com.zuju.zujuassessment.main.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.zuju.zujuassessment.databinding.ItemTeamPlayBinding
import com.zuju.zujuassessment.main.teams.TeamPlayerAdapter.TeamPlayerViewHolder

class TeamPlayerAdapter(
    private val teamPlayers: List<TeamPlayerUi>,
    private val onItemSelected: OnTeamSelected,
) : ListAdapter<TeamPlayerUi, TeamPlayerViewHolder>(diffCallback) {

    private lateinit var inflater: LayoutInflater

    override fun getItemCount(): Int = teamPlayers.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamPlayerViewHolder {
        if (!::inflater.isInitialized) inflater = LayoutInflater.from(parent.context)
        val binding = ItemTeamPlayBinding.inflate(inflater, parent, false)
        return TeamPlayerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamPlayerViewHolder, position: Int) {
        holder.bindData(teamPlayers[position])
        holder.onItemClicked(onItemSelected, teamPlayers[position])
    }

    fun interface OnTeamSelected {
        operator fun invoke(team: TeamPlayerUi)
    }

    inner class TeamPlayerViewHolder(
        private val binding: ItemTeamPlayBinding
    ) : ViewHolder(binding.root) {
        fun bindData(data: TeamPlayerUi) {
            binding.imgTeam.load(data.avatar)
            binding.tvTeamName.text = data.name
        }

        fun onItemClicked(onItemSelected: OnTeamSelected, teamPlayer: TeamPlayerUi) {
            binding.root.setOnClickListener { onItemSelected(teamPlayer) }
        }
    }

    companion object {
        private val diffCallback = object : ItemCallback<TeamPlayerUi>() {
            override fun areItemsTheSame(oldItem: TeamPlayerUi, newItem: TeamPlayerUi): Boolean {
                return oldItem.id == newItem.id //id is the unique field, so we can use it to define 2 different objects
            }

            override fun areContentsTheSame(oldItem: TeamPlayerUi, newItem: TeamPlayerUi): Boolean {
                return oldItem == newItem //Just use compare by equals, because the data class ready implement compare 2 object by fields
            }
        }
    }
}
