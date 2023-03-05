package com.zuju.zujuassessment.main.teams

import android.view.LayoutInflater
import com.zuju.zujuassessment.databinding.FragmentSelectTeamBinding
import com.zuju.zujuassessment.main.base.FullScreenDialogFragment
import com.zuju.zujuassessment.main.teams.TeamPlayerAdapter.OnTeamSelected
import kotlin.random.Random

class SelectTeamFragment(
    private val onTeamSelected: OnTeamSelected
) : FullScreenDialogFragment<FragmentSelectTeamBinding>() {

    override fun getViewBinding(inflater: LayoutInflater): FragmentSelectTeamBinding {
        return FragmentSelectTeamBinding.inflate(inflater)
    }

    override fun getLeadingView() = binding.imgClose

    override fun initView() {
        val list = MutableList(100) {
            TeamPlayerUi(
                name = "Hello",
                id = Random.nextLong().toString(),
                avatar = "https://tstzj.s3.amazonaws.com/eagle.png",
            )
        }

        binding.rcvTeamPlayer.adapter = TeamPlayerAdapter(list) {
            onTeamSelected(it)
            dismiss()
        }
    }
}
