package com.zuju.features.match.upcoming

import android.view.LayoutInflater
import androidx.fragment.app.activityViewModels
import com.zuju.features.core.base.fragments.BaseFragment
import com.zuju.features.core.base.utils.observeResultFlow
import com.zuju.features.core.base.utils.showToast
import com.zuju.features.match.R
import com.zuju.features.match.databinding.FragmentUpcomingBinding
import com.zuju.features.match.sharedviewmodel.MatchSharedViewModel
import com.zuju.features.match.upcoming.reminder.MatchReminder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpcomingMatchFragment : BaseFragment<FragmentUpcomingBinding>() {

    private lateinit var upcomingMatchAdapter: UpcomingMatchAdapter
    private val sharedViewModel: MatchSharedViewModel by activityViewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentUpcomingBinding {
        return FragmentUpcomingBinding.inflate(inflater)
    }

    override fun initView() {
        upcomingMatchAdapter = UpcomingMatchAdapter(::setAlarmForMatch)

        binding.rcvUpcomingMatch.apply {
            adapter = upcomingMatchAdapter
            setHasFixedSize(true)
        }
    }

    override fun initObserver() {
        observeResultFlow(sharedViewModel.upcomingMatchFlow, ::onMatchUpdated)
    }

    private fun onMatchUpdated(newMatches: List<UpcomingMatchUi>) {
        upcomingMatchAdapter.submitList(newMatches)
    }

    private fun setAlarmForMatch(upcomingMatch: UpcomingMatchUi) {
        MatchReminder.remind(requireContext(), upcomingMatch)
        showToast(R.string.created_remind)
    }
}
