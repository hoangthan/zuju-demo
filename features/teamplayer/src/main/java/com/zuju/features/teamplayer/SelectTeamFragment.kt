package com.zuju.features.teamplayer

import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.zuju.features.core.base.fragments.FullScreenDialogFragment
import com.zuju.features.core.base.utils.ProgressScreen
import com.zuju.features.core.base.utils.consumeResultFlow
import com.zuju.features.teamplayer.TeamPlayerAdapter.OnTeamSelected
import com.zuju.features.teamplayer.databinding.FragmentSelectTeamBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectTeamFragment(
    private val onTeamSelected: OnTeamSelected
) : FullScreenDialogFragment<FragmentSelectTeamBinding>(), ProgressScreen {

    private lateinit var teamAdapter: TeamPlayerAdapter
    private val viewModel: SelectTeamViewModel by viewModels()

    override fun getViewBinding(inflater: LayoutInflater): FragmentSelectTeamBinding {
        return FragmentSelectTeamBinding.inflate(inflater)
    }

    override fun getLeadingView() = binding.imgClose

    override fun initView() {
        if (!::teamAdapter.isInitialized) {
            teamAdapter = TeamPlayerAdapter {
                onTeamSelected(it)
            }
        }

        binding.rcvTeamPlayer.apply {
            adapter = teamAdapter
            setHasFixedSize(true)
        }
    }

    override val loadingView by lazy { binding.loadingView }

    override fun initViewListener() {
        binding.tvReset.setOnClickListener(this)
    }

    override fun onViewClicked(view: View) {
        when (view.id) {
            binding.tvReset.id -> onTeamSelected(null)
        }
    }

    override fun initObserver() {
        consumeResultFlow(viewModel.teamFlow, ::updateListTeam)
    }

    private fun updateListTeam(teams: List<TeamPlayerUi>) {
        teamAdapter.submitList(teams)
    }
}
