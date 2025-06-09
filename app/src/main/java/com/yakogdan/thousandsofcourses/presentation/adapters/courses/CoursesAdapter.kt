package com.yakogdan.thousandsofcourses.presentation.adapters.courses

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.yakogdan.thousandsofcourses.presentation.adapters.delegate.AdapterDelegate
import com.yakogdan.thousandsofcourses.presentation.adapters.delegate.DelegateAdapterItemCallback
import com.yakogdan.thousandsofcourses.presentation.adapters.delegate.DelegateItem

class CoursesAdapter :
    ListAdapter<DelegateItem, ViewHolder>(DelegateAdapterItemCallback()) {

    private val delegates: MutableList<AdapterDelegate> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        delegates[viewType].onCreateViewHolder(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        delegates[getItemViewType(position)].onBindViewHolder(
            holder = holder,
            delegateItem = getItem(position),
            position = position
        )
    }

    fun addDelegate(delegate: AdapterDelegate) {
        delegates.add(delegate)
    }

    override fun getItemViewType(position: Int): Int =
        delegates.indexOfFirst { it.isOfViewType(currentList[position]) }
}