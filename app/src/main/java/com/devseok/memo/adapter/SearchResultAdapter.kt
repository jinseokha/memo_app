package com.devseok.memo.adapter

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.databinding.ItemHolderBinding
import com.devseok.memo.view.MemoEditActivity
import com.devseok.memo.viewmodel.SearchResultViewModel

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-05-19
 * @desc
 */
class SearchResultAdapter(val activity : Activity, val itemList : MutableList<Memo>,
                          val searchResultViewModel: SearchResultViewModel) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHolderBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(val binding : ItemHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Memo) {
            if (item.secretMode) {
                binding.layoutLock.visibility = View.VISIBLE
                binding.textViewContent.visibility = View.GONE
                binding.textViewTime.visibility = View.GONE
            } else {
                binding.layoutLock.visibility = View.GONE
                binding.textViewContent.visibility = View.VISIBLE
                binding.textViewTime.visibility = View.VISIBLE
            }

            binding.textViewContent.text = item.memo
            binding.textViewTime.text = item.update_time

            var drawable_color = ContextCompat.getDrawable(activity, R.drawable.ic_menu_background) as GradientDrawable?
            drawable_color!!.setColor(Color.parseColor(item.color))
            binding.imgColor.setImageDrawable(drawable_color)


            binding.layoutAll.setOnClickListener {
                val intent = Intent(activity, MemoEditActivity::class.java)
                intent.putExtra("memo", item)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                activity.startActivity(intent)
                activity.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
            }
        }
    }
}