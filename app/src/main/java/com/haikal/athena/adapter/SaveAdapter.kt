package com.haikal.athena.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.haikal.athena.R
import com.haikal.athena.data.local.entity.Save
import com.haikal.athena.databinding.ItemSaveBinding

class SaveAdapter : ListAdapter<Save, SaveAdapter.SaveViewHolder>(DIFF_CALLBACK) {

    var onClick: ((Save) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaveViewHolder {
        val binding = ItemSaveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SaveViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaveViewHolder, position: Int) {
        val listSave = getItem(position)
        holder.bind(listSave, onClick)
    }

    class SaveViewHolder(private val binding: ItemSaveBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Save, onClick: ((Save) -> Unit)?) {
            binding.tvPrediction.text = item.prediction
            binding.tvScore.text = item.score
            binding.ivImage.setImageURI(item.image?.toUri())
            binding.buttonDelete.setOnClickListener {
                onClick?.invoke(item)
                Toast.makeText(
                    binding.root.context,
                    binding.root.context.getString(R.string.save_deleted),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Save>() {
            override fun areItemsTheSame(oldItem: Save, newItem: Save): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Save, newItem: Save): Boolean {
                return oldItem == newItem
            }
        }
    }
}