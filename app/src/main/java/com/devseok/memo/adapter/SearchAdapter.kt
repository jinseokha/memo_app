package com.devseok.memo.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.databinding.ItemSearchHolderBinding
import com.devseok.memo.view.SearchResultActivity
import com.devseok.memo.viewmodel.SearchViewModel

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-05-18
 * @desc
 */
class SearchAdapter(val activity : Activity, val itemList : MutableList<Memo>,
                    val searchViewModel: SearchViewModel) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSearchHolderBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(val binding : ItemSearchHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Memo) {
            binding.tvSearch.text = item.memo

            binding.layoutAll.setOnClickListener {
                val intent = Intent(activity, SearchResultActivity::class.java)
                intent.putExtra("memo", item.memo)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                activity.startActivity(intent)
                activity.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left)
            }
        }
    }
}