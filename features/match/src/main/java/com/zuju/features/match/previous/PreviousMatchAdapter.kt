package com.zuju.features.match.previous

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.zuju.data.core.utils.DateTimeUtils
import com.zuju.features.match.databinding.ItemPreviousMatchBinding
import com.zuju.features.match.previous.PreviousMatchAdapter.PreviousMatchViewHolder

class PreviousMatchAdapter(
    private val callback: OnHighLightSelected,
) : ListAdapter<PreviousMatchUi, PreviousMatchViewHolder>(diffCallback) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviousMatchViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }

        val binding = ItemPreviousMatchBinding.inflate(inflater, parent, false)
        return PreviousMatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PreviousMatchViewHolder, position: Int) {
        holder.bindData(getItem(position))
        holder.onHighLightSelected(getItem(position), callback)
    }

    fun interface OnHighLightSelected {
        operator fun invoke(match: PreviousMatchUi)
    }

    class PreviousMatchViewHolder(
        private val binding: ItemPreviousMatchBinding,
    ) : ViewHolder(binding.root) {

        fun bindData(match: PreviousMatchUi) {
            binding.tvMatchTime.text = DateTimeUtils.formatDateString(
                dateString = match.date,
                sourceFormat = DateTimeUtils.ISO_8601,
                destinationFormat = DateTimeUtils.DATE_HOUR,
            )

            binding.tvTeamHome.text = match.homeTeamName
            binding.tvTeamAway.text = match.awayTeamName
            binding.tvMatchContent.text = match.description

            binding.imgTeamHome.load(match.homeTeamAvatar)
            binding.imgTeamAway.load(match.awayTeamAvatar)

            binding.imgHomeWin.isVisible = match.homeTeamName == match.winner
            binding.imgAwayWin.isVisible = match.awayTeamName == match.winner
        }

        fun onHighLightSelected(match: PreviousMatchUi, callback: OnHighLightSelected) {
            binding.imgHighLight.setOnClickListener { callback(match) }
        }
    }

    companion object {
        private val diffCallback = object : ItemCallback<PreviousMatchUi>() {
            override fun areItemsTheSame(
                oldItem: PreviousMatchUi,
                newItem: PreviousMatchUi,
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: PreviousMatchUi,
                newItem: PreviousMatchUi,
            ): Boolean = oldItem == newItem
        }
    }
}
