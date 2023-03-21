package com.cookandroid.instagram_android_moon.src.comment.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cookandroid.instagram_android_moon.databinding.ItemRecyclerCommentBinding
import com.cookandroid.instagram_android_moon.src.comment.LikeComment.LikeCommentInterface
import com.cookandroid.instagram_android_moon.src.comment.LikeComment.LikeCommentService
import com.cookandroid.instagram_android_moon.src.comment.model.CommentsResponse
import com.cookandroid.instagram_android_moon.src.comment.model.ResultComments
import com.cookandroid.instagram_android_moon.src.comment.reply.ReplyAdapterInterface
import com.cookandroid.instagram_android_moon.src.comment.reply.ReplyService
import com.cookandroid.instagram_android_moon.src.comment.reply.adapter.ReplyAdapter
import com.cookandroid.instagram_android_moon.src.main.home.likePosting.model.LikeCommentResponse
import com.cookandroid.instagram_android_moon.util.ElapsedTimeFunction

class CommentAdapter(
    val context: Context,
    private val resultComments: MutableList<ResultComments>,
    private val listener: ReplyingClickListener
) : RecyclerView.Adapter<CommentAdapter.ViewHolder>(), ReplyAdapterInterface, LikeCommentInterface {
    private lateinit var view: ItemRecyclerCommentBinding
    private lateinit var _groupId: String
    inner class ViewHolder(val binding: ItemRecyclerCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ResultComments) {
            _groupId = ""
            Glide.with(context).load(item.profilePicture).into(binding.ivCommentCommentProfileImage)
            binding.apply {
                tvCommentCommentUserName.text = item.profileName
                tvCommentCommentContent.text = item.comment
                tvCommentCommentPostDate.text = ElapsedTimeFunction().run {
                    calculationTime(this.dateTimeToMillSec(item.createdAt))
                }
                tvCommentCommentLikeCount.text = item.likeCount.toString()
                var like = item.likeCount
                if (item.likeCount > 0) tvCommentCommentLikeCount.visibility = View.VISIBLE
                if (item.likeOn.on == 1) ckbxCommentCommentLike.isChecked = true
                binding.ckbxCommentCommentLike.setOnClickListener {
                    when((it as AppCompatCheckBox).isChecked) {
                        true -> {
                            like++
                            if(item.likeOn.id == 0) {
                                LikeCommentService(this@CommentAdapter).tryPostLikeComment(item.commentId)
                            } else {
                                LikeCommentService(this@CommentAdapter).tryPatchEditLikeComment(item.likeOn.id, true)
                            }
                            if(like > 0 ) tvCommentCommentLikeCount.visibility = View.VISIBLE
                        }
                        false -> {
                            like--
                            LikeCommentService(this@CommentAdapter).tryPatchEditLikeComment(item.likeOn.id, false)
                            if(like == 0 ) tvCommentCommentLikeCount.visibility = View.INVISIBLE
                        }
                    }
                    binding.tvCommentCommentLikeCount.text = ""+like
                }

                ivCommentCommentProfileImage.clipToOutline = true
                tvCommentCommentReply.setOnClickListener {
                    listener.onReplyingClick(item.commentId)
                    Log.d("GROUPIDTEST", item.commentId.toString())
                    listener.onReplyingClick(item.profileName)
                }
                if (item.bigCommentCount > 0) {
                    constCommentCheckReplies.visibility = View.VISIBLE
                    tvCommentCheckRepliesCount.text = "답글 " + item.bigCommentCount +"개 보기"
                }
                constCommentCheckReplies.setOnClickListener {
                    ReplyService(this@CommentAdapter).tryGetReplies(item.commentId)
                    constCommentCheckReplies.visibility = View.GONE
                    recyclerCommentReply.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = ItemRecyclerCommentBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.bind(resultComments[position])
    }

    override fun getItemCount(): Int {
        return resultComments.size
    }

    interface ReplyingClickListener {
        fun onReplyingClick(group_id: Int)
        fun onReplyingClick(nickname: String)
    }

    override fun onGetRepliesSuccess(response: CommentsResponse) {
        val resultReplies = response.result
        view.recyclerCommentReply.apply {
            adapter = ReplyAdapter(context, resultReplies, object: ReplyAdapter.ReplyingClickListener{
                override fun onReplyingClick(group_id: Int) {
                    _groupId = group_id.toString()
                    listener.onReplyingClick(_groupId.toInt())
                    Log.d("GROUPIDTEST", _groupId)
                }

                override fun onReplyingClick(nickname: String) {
                    listener.onReplyingClick(nickname)
                }
            })
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onGetRepliesFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("SignInError", message)
    }

    override fun onPostLikeCommentSuccess(response: LikeCommentResponse) {
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onPostLikeCommentFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("SignInError", message)
    }

    override fun onPatchEditLikeCommentSuccess(response: LikeCommentResponse) {
        response.message?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }

    override fun onPatchEditLikeCommentFailure(message: String) {
        Toast.makeText(context, "오류 : $message", Toast.LENGTH_SHORT).show()
        Log.d("SignInError", message)
    }
}