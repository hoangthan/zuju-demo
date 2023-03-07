package com.zuju.features.media.video

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.zuju.features.core.base.utils.showToast
import com.zuju.features.media.R
import com.zuju.features.media.databinding.ActivityVideoPlayerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //To play video full screen at the landscape mode
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playVideo()
    }

    private fun playVideo() {
        val videoUrl = intent.getStringExtra(URL_KEY)
        videoUrl ?: run {
            showToast(R.string.not_found_video)
            finish()
            return
        }

        val videoView = binding.videoView
        val uri = Uri.parse(videoUrl)
        videoView.setVideoURI(uri)

        val mediaController = MediaController(this)
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)

        videoView.start()
    }

    companion object {
        const val URL_KEY = "video_url"
    }
}
