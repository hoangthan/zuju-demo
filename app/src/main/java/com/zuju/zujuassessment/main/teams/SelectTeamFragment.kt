package com.zuju.zujuassessment.main.teams

import android.view.LayoutInflater
import com.zuju.zujuassessment.databinding.FragmentSelectTeamBinding
import com.zuju.zujuassessment.main.fragments.FullScreenDialogFragment
import kotlin.random.Random

class SelectTeamFragment : FullScreenDialogFragment<FragmentSelectTeamBinding>() {

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
        binding.rcvTeamPlayer.setHasFixedSize(true)
        binding.rcvTeamPlayer.adapter = TeamPlayerAdapter(list) {
            //Call to caller callback
            dismiss()
        }
    }
}
