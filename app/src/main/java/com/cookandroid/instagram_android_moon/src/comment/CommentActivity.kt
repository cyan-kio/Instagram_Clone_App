package com.cookandroid.instagram_android_moon.src.comment

import android.os.Bundle
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivityCommentBinding

class CommentActivity : BaseActivity<ActivityCommentBinding>(ActivityCommentBinding::inflate) {
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
}