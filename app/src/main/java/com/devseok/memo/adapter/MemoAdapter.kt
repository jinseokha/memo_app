package com.devseok.memo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devseok.data.model.Memo
import com.devseok.memo.databinding.ItemHolderBinding
import com.devseok.memo.viewmodel.MainViewModel

/**
 * @author Ha Jin Seok
 * @email seok270@dahami.com
 * @created 2022-05-10
 * @desc
 */
class MemoAdapter(val context : Context, val itemList : MutableList<Memo>,
                  val mainViewModel : MainViewModel) : RecyclerView.Adapter<MemoAdapter.ViewHolder>() {

    var lastEditidx : Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHolderBinding.inflate(LayoutInflater.from(context), parent, false)
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
            binding.textViewContent.text = item.memo
            binding.textViewTime.text = item.update_time
        }
    }
}