package com.zuju.features.match.upcoming

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.zuju.data.core.utils.DateTimeUtils
import com.zuju.features.match.databinding.ItemUpcomingMatchBinding
import com.zuju.features.match.upcoming.UpcomingMatchAdapter.UpcomingMatchViewHolder

class UpcomingMatchAdapter(
    private val callback: OnRemindSelected,
) : ListAdapter<UpcomingMatchUi, UpcomingMatchViewHolder>(diffCallback) {

    private lateinit var inflater: LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingMatchViewHolder {
        if (!::inflater.isInitialized) {
            inflater = LayoutInflater.from(parent.context)
        }

        val binding = ItemUpcomingMatchBinding.inflate(inflater, parent, false)
        return UpcomingMatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UpcomingMatchViewHolder, position: Int) {
        holder.bindData(getItem(position))
        holder.onViewClicked(callback, getItem(position))
    }

    fun interface OnRemindSelected {
        operator fun invoke(match: UpcomingMatchUi)
    }

    inner class UpcomingMatchViewHolder(
        private val binding: ItemUpcomingMatchBinding,
    ) : ViewHolder(binding.root) {

        fun bindData(upcomingMatchUi: UpcomingMatchUi) {
            binding.tvMatchTime.text = DateTimeUtils.formatDateString(
                dateString = upcomingMatchUi.date,
                sourceFormat = DateTimeUtils.ISO_8601,
                destinationFormat = DateTimeUtils.DATE_HOUR,
            )

            binding.tvTeamHome.text = upcomingMatchUi.homeTeamName
            binding.tvTeamAway.text = upcomingMatchUi.awayTeamName
            binding.tvDescription.text = upcomingMatchUi.description

            binding.imgTeamHome.load(upcomingMatchUi.homeTeamAvatar)
            binding.imgTeamAway.load(upcomingMatchUi.awayTeamAvatar)
        }

        fun onViewClicked(callback: OnRemindSelected, upcomingMatchUi: UpcomingMatchUi) {
            binding.imgNotification.setOnClickListener { callback(upcomingMatchUi) }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UpcomingMatchUi>() {
            override fun areItemsTheSame(
                oldItem: UpcomingMatchUi,
                newItem: UpcomingMatchUi,
            ): Boolean = oldItem == newItem

            override fun areContentsTheSame(
                oldItem: UpcomingMatchUi,
                newItem: UpcomingMatchUi,
            ): Boolean = oldItem == newItem
        }
    }
}
