package com.devseok.memo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.devseok.domain.model.DomainNotice
import com.devseok.memo.R
import com.devseok.memo.databinding.ItemNoticeTitleBinding
import com.devseok.memo.viewmodel.NoticeViewModel

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-20
 * @desc
 */
class NoticeRecyclerViewAdapter (
    private val viewModel : NoticeViewModel
) : RecyclerView.Adapter<NoticeRecyclerViewAdapter.NoticeRecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemNoticeTitleBinding>(
            layoutInflater, R.layout.item_notice_title, parent, false)


        return NoticeRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoticeRecyclerViewHolder, position: Int) {
        holder.bind(viewModel.noticeList[position])
    }

    override fun getItemCount(): Int {
        return viewModel.noticeList.size
    }

    inner class NoticeRecyclerViewHolder(val binding : ItemNoticeTitleBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data : DomainNotice) {
            binding.data = data
            binding.executePendingBindings()
        }
    }
}