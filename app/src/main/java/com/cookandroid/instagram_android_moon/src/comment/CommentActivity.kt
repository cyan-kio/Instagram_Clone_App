package com.cookandroid.instagram_android_moon.src.comment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.config.BaseActivity
import com.cookandroid.instagram_android_moon.databinding.ActivityCommentBinding
import com.cookandroid.instagram_android_moon.src.comment.adapter.CommentAdapter
import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import com.cookandroid.instagram_android_moon.src.comment.model.PostWriteCommentRequest
import com.cookandroid.instagram_android_moon.src.comment.model.WriteCommentResponse
import com.cookandroid.instagram_android_moon.util.ElapsedTimeFunction

class CommentActivity : BaseActivity<ActivityCommentBinding>(ActivityCommentBinding::inflate),
    CommentActivityInterface {
    val RED_HEART = 0x2764
    val RAISING_HANDS = 0x1F64C
    val FIRE = 0x1F525
    val CLAPPING_HANDS = 0x1F44F
    val CRYING_FACE = 0x1F622
    val HEART_EYES = 0x1F60D
    val OPEN_MOUTH = 0x1F62E
    val TEARS_OF_JOY = 0x1F602

    private lateinit var groupId: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        groupId = ""

        // receive postId
        val postId = intent.getIntExtra("postId", 0)

        Log.d("POSTIDCHECK", postId.toString())
        CommentService(this).tryGetComments(postId)

        // receive extras
        val profileName = intent.getStringExtra("profileName")
        if (profileName != null) binding.tvCommentPostingUserName.text = profileName
        val profilePicture = intent.getStringExtra("profilePicture")
        if (profilePicture != null) Glide.with(this).load(profilePicture)
            .into(binding.ivCommentPostingProfileImage)
        binding.ivCommentPostingProfileImage.clipToOutline = true
        val createdAt = intent.getStringExtra("createdAt")
        if (createdAt != null) {
            binding.tvCommentPostingPostDate.text = ElapsedTimeFunction().run {
                calculationTime(this.dateTimeToMillSec(createdAt))
            }
        }
        val content = intent.getStringExtra("content")
        if (content != null) binding.tvCommentPostingContent.text = content

        initEmoji()
        setEmojiInEdt()

        if (binding.edtCommentWrite.text.toString() == "") binding.btnCommentPost.alpha = 0.3f
        binding.edtCommentWrite.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if (binding.edtCommentWrite.text.toString() == "") binding.btnCommentPost.alpha = 0.3f
                else binding.btnCommentPost.alpha = 1f
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.edtCommentWrite.text.toString() == "") binding.btnCommentPost.alpha = 0.3f
                else binding.btnCommentPost.alpha = 1f
            }

            override fun afterTextChanged(s: Editable?) {
                if (binding.edtCommentWrite.text.toString() == "") binding.btnCommentPost.alpha = 0.3f
                else binding.btnCommentPost.alpha = 1f
            }
        })
        binding.btnCommentPost.setOnClickListener {

                if(binding.edtCommentWrite.text.toString() == ""){} else {
                    if (groupId == "") {
                        CommentService(this).tryPostWriteComment(
                            PostWriteCommentRequest(
                                postId = postId,
                                comment = binding.edtCommentWrite.text.toString()
                            )
                        )
                    } else {
                        CommentService(this).tryPostWriteComment(
                            PostWriteCommentRequest(
                                postId = postId, groupId.toInt(),
                                comment = binding.edtCommentWrite.text.toString()
                            )
                        )
                    }
                    binding.edtCommentWrite.setText("")
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(binding.edtCommentWrite.windowToken, 0)
                }
            }
    }



    private fun initEmoji() {
        binding.tvCommentBtmEmojiFire.text = getEmojiByUnicode(FIRE)
        binding.tvCommentBtmEmojiRedHeart.text = getEmojiByUnicode(RED_HEART)
        binding.tvCommentBtmEmojiRaisingHands.text = getEmojiByUnicode(RAISING_HANDS)
        binding.tvCommentBtmEmojiCryingFace.text = getEmojiByUnicode(CRYING_FACE)
        binding.tvCommentBtmEmojiClappingHands.text = getEmojiByUnicode(CLAPPING_HANDS)
        binding.tvCommentBtmEmojiOpenMouth.text = getEmojiByUnicode(OPEN_MOUTH)
        binding.tvCommentBtmEmojiHeartEyes.text = getEmojiByUnicode(HEART_EYES)
        binding.tvCommentBtmEmojiTearsOfJoy.text = getEmojiByUnicode(TEARS_OF_JOY)
    }

    private fun setEmojiInEdt() {
        binding.tvCommentBtmEmojiRedHeart.setOnClickListener {
            val text = binding.edtCommentWrite.text.toString()
            binding.edtCommentWrite.setText(text + getEmojiByUnicode(RED_HEART))
        }
        binding.tvCommentBtmEmojiRaisingHands.setOnClickListener {
            val text = binding.edtCommentWrite.text.toString()
            binding.edtCommentWrite.setText(text + getEmojiByUnicode(RAISING_HANDS))
        }
        binding.tvCommentBtmEmojiFire.setOnClickListener {
            val text = binding.edtCommentWrite.text.toString()
            binding.edtCommentWrite.setText(text + getEmojiByUnicode(FIRE))
        }
        binding.tvCommentBtmEmojiClappingHands.setOnClickListener {
            val text = binding.edtCommentWrite.text.toString()
            binding.edtCommentWrite.setText(text + getEmojiByUnicode(CLAPPING_HANDS))
        }
        binding.tvCommentBtmEmojiCryingFace.setOnClickListener {
            val text = binding.edtCommentWrite.text.toString()
            binding.edtCommentWrite.setText(text + getEmojiByUnicode(CRYING_FACE))
        }
        binding.tvCommentBtmEmojiHeartEyes.setOnClickListener {
            val text = binding.edtCommentWrite.text.toString()
            binding.edtCommentWrite.setText(text + getEmojiByUnicode(HEART_EYES))
        }
        binding.tvCommentBtmEmojiOpenMouth.setOnClickListener {
            val text = binding.edtCommentWrite.text.toString()
            binding.edtCommentWrite.setText(text + getEmojiByUnicode(OPEN_MOUTH))
        }
        binding.tvCommentBtmEmojiTearsOfJoy.setOnClickListener {
            val text = binding.edtCommentWrite.text.toString()
            binding.edtCommentWrite.setText(text + getEmojiByUnicode(TEARS_OF_JOY))
        }
    }

    fun getEmojiByUnicode(unicode: Int): String = String(Character.toChars(unicode))

    override fun onGetCommentsSuccess(response: CommentsResponse) {
        val commentsResponse = response.result
        binding.recyclerCommentComment.apply {
            adapter = CommentAdapter(this@CommentActivity, commentsResponse, object: CommentAdapter.ReplyingClickListener{
                override fun onReplyingClick(group_id: Int) {
                    groupId = group_id.toString()
                }
                override fun onReplyingClick(nickname: String) {
                    binding.constDialogWriteReply.visibility = View.VISIBLE
                    binding.tvWritingReply.text = nickname+"님에게 답글 남기는 중"
                    binding.ivWritingReplyCancel.setOnClickListener {
                        groupId = ""
                        binding.constDialogWriteReply.visibility = View.GONE
                        binding.edtCommentWrite.setText("")
                    }
                    binding.edtCommentWrite.setText("@"+nickname+" ")
                    binding.edtCommentWrite.post(Runnable {
                        binding.edtCommentWrite.setFocusableInTouchMode(true)
                        binding.edtCommentWrite.requestFocus()
                        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.showSoftInput(binding.edtCommentWrite, 0)
                    })
                    binding.edtCommentWrite.setSelection(binding.edtCommentWrite.length())
                }
            })
            layoutManager =
                LinearLayoutManager(this@CommentActivity, LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun onGetCommentsFailure(message: String) {
        showCustomToast("오류 : $message")
        Log.d("ProfileError", message)
    }

    override fun onPostWriteCommentSuccess(response: WriteCommentResponse) {
        response.message?.let { showCustomToast(it) }
    }

    override fun onPostWriteCommentFailure(message: String) {
         showCustomToast("오류 : $message")
         Log.d("SignInError", message)
    }
}