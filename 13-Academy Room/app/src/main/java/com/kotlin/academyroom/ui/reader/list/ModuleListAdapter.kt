package com.kotlin.academyroom.ui.reader.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.academyroom.data.source.local.entity.ModuleEntity
import com.kotlin.academyroom.databinding.ItemsModuleListCustomBinding

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class ModuleListAdapter internal constructor(private val listener: MyAdapterClicklistener) :
    RecyclerView.Adapter<ModuleListAdapter.ModuleViewholder>() {

    private val listModules = ArrayList<ModuleEntity>()

    internal fun setModules(modules: List<ModuleEntity>?) {
        if (modules == null) return
        this.listModules.clear()
        this.listModules.addAll(modules)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewholder {
        val binding =
            ItemsModuleListCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewholder(binding)
    }

    override fun onBindViewHolder(holder: ModuleViewholder, position: Int) {
        val module = listModules[position]
        holder.bind(module)
        holder.itemView.setOnClickListener {
            listener.onItemClicked(
                holder.adapterPosition,
                listModules[holder.adapterPosition].moduleId
            )
        }
    }

    override fun getItemCount(): Int {
        return listModules.size
    }

    class ModuleViewholder(private val binding: ItemsModuleListCustomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: ModuleEntity) {
            binding.textModuleTitle.text = module.title
        }

    }

    internal interface MyAdapterClicklistener {
        fun onItemClicked(position: Int, moduleId: String)
    }

}