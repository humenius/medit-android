package com.github.kereis.medit.ui.explorer.recent

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.documentfile.provider.DocumentFile
import androidx.recyclerview.widget.RecyclerView
import com.github.kereis.medit.databinding.FragmentFileExplorerRecentListElementBinding
import com.github.kereis.medit.ui.AbstractViewHolder
import com.github.kereis.medit.ui.explorer.FileExplorerFileElementViewHolder

class FileExplorerRecentListAdapter(
    private val context: Context
) : RecyclerView.Adapter<AbstractViewHolder<*>>() {

    private var fileList = listOf<DocumentFile>()

    fun setFileList(fileList: List<DocumentFile>) {
        this.fileList = fileList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder<*> {
        val itemBinding = FragmentFileExplorerRecentListElementBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return FileExplorerFileElementViewHolder(null, itemBinding)
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<*>, position: Int) {
        when (holder) {
            is FileExplorerFileElementViewHolder -> holder.bind(fileList[position])
        }
    }

    override fun getItemCount(): Int = fileList.size
}
