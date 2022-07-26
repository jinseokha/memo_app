package com.devseok.memo.adapter

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.devseok.data.model.Memo
import com.devseok.memo.R
import com.devseok.memo.databinding.ItemGridHolderBinding
import com.devseok.memo.view.MemoEditActivity
import com.devseok.memo.viewmodel.MainViewModel
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author Ha Jin Seok
 * @email seok270@gmail.com
 * @created 2022-07-21
 * @desc
 */
class MemoStaggeredAdapter (val activity : Activity, val itemList : MutableList<Memo>,
                            val mainViewModel : MainViewModel
) : RecyclerView.Adapter<MemoStaggeredAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoStaggeredAdapter.ViewHolder {
        val binding = ItemGridHolderBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MemoStaggeredAdapter.ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class ViewHolder(val binding : ItemGridHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Memo) {

            if (item.secretMode) {
                binding.textViewContent.visibility = View.GONE
                binding.textViewTime.visibility = View.GONE
            } else {
                binding.textViewContent.visibility = View.VISIBLE
                binding.textViewTime.visibility = View.VISIBLE
            }

            binding.textViewContent.text = item.memo
            //binding.textViewTime.text = item.update_time

            // SimpleDateFormat("yyyy.MM.dd.(EE) a hh:mm")
            var date_before : String = item.update_time
            var date_after : String? = formateDateFromstring("yyyy.MM.dd.(EE) a hh:mm",
                "yy.MM.dd. a hh:mm", date_before)

            binding.textViewTime.text = date_after

            var drawable_color = ContextCompat.getDrawable(activity, R.drawable.ic_menu_grid_background) as GradientDrawable?
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

    fun formateDateFromstring(
        inputFormat: String?,
        outputFormat: String?,
        inputDate: String?
    ): String? {
        var parsed: Date? = null
        var outputDate = ""
        val df_input = SimpleDateFormat(inputFormat, Locale.getDefault())
        val df_output = SimpleDateFormat(outputFormat, Locale.getDefault())
        try {
            parsed = df_input.parse(inputDate)
            outputDate = df_output.format(parsed)
        } catch (e: ParseException) {

        }
        return outputDate
    }
}