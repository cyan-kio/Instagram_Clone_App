package com.cookandroid.instagram_android_moon.src.comment

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivityCommentBinding
import com.cookandroid.instagram_android_moon.src.comment.adapter.CommentAdapter
import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import com.cookandroid.instagram_android_moon.util.ElapsedTimeFunction

class CommentActivity : BaseActivity<ActivityCommentBinding>(ActivityCommentBinding::inflate), CommentActivityInterface {
    val RED_HEART = 0x2764
    val RAISING_HANDS = 0x1F64C
    val FIRE = 0x1F525
    val CLAPPING_HANDS = 0x1F44F
    val CRYING_FACE = 0x1F622
    val HEART_EYES = 0x1F60D
    val OPEN_MOUTH = 0x1F62E
    val TEARS_OF_JOY = 0x1F602

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // receive postId
        val postId = intent.getIntExtra("postId", 0)

        Log.d("POSTIDCHECK", postId.toString())
        CommentService(this).tryGetComments(postId)

        // receive extras
        val profileName = intent.getStringExtra("profileName")
        if (profileName != null) binding.tvCommentPostingUserName.text = profileName
        val profilePicture = intent.getStringExtra("profilePicture")
        if (profilePicture != null) Glide.with(this).load(profilePicture).into(binding.ivCommentPostingProfileImage)
        binding.ivCommentPostingProfileImage.clipToOutline = true
        val createdAt = intent.getStringExtra("createdAt")
        if (createdAt != null)  {
            binding.tvCommentPostingPostDate.text = ElapsedTimeFunction().run {
                calculationTime(this.dateTimeToMillSec(createdAt))
            }
        }
        val content = intent.getStringExtra("content")
        if (content != null) binding.tvCommentPostingContent.text = content

        binding.tvCommentBtmEmojiFire.text = getEmojiByUnicode(FIRE)
        binding.tvCommentBtmEmojiRedHeart.text = getEmojiByUnicode(RED_HEART)
        binding.tvCommentBtmEmojiRaisingHands.text = getEmojiByUnicode(RAISING_HANDS)
        binding.tvCommentBtmEmojiCryingFace.text = getEmojiByUnicode(CRYING_FACE)
        binding.tvCommentBtmEmojiClappingHands.text = getEmojiByUnicode(CLAPPING_HANDS)
        binding.tvCommentBtmEmojiOpenMouth.text = getEmojiByUnicode(OPEN_MOUTH)
        binding.tvCommentBtmEmojiHeartEyes.text = getEmojiByUnicode(HEART_EYES)
        binding.tvCommentBtmEmojiTearsOfJoy.text = getEmojiByUnicode(TEARS_OF_JOY)

    }

    fun getEmojiByUnicode(unicode: Int) :String = String(Character.toChars(unicode))

    override fun onGetCommentsSuccess(response: CommentsResponse) {
        val commentsResponse = response.result
        binding.recyclerCommentComment.apply {
            adapter = CommentAdapter(this@CommentActivity, commentsResponse)
            layoutManager = LinearLayoutManager(this@CommentActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onGetCommentsFailure(message: String) {
        TODO("Not yet implemented")
    }
}