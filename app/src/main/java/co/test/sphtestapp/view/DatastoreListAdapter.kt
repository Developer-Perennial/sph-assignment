package co.test.sphtestapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.test.sphtestapp.data.network.response.Record
import co.test.sphtestapp.databinding.ItemDatastoreRecordBinding

class DatastoreListAdapter(
    private val yearWiseVolumeData: ArrayList<Record>,
    val clickHandler: ClickHandler
) : RecyclerView.Adapter<DatastoreListAdapter.DatastoreListHolder>() {

    inner class DatastoreListHolder(private val binding: ItemDatastoreRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: Record, position: Int) {
            binding.record = record
            binding.listener = clickHandler
            binding.position = position
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatastoreListHolder {
        val itemBinding: ItemDatastoreRecordBinding =
            ItemDatastoreRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DatastoreListHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DatastoreListHolder, position: Int) {
        holder.bind(yearWiseVolumeData[position], position)
    }

    override fun getItemCount(): Int {
        return yearWiseVolumeData.size
    }
}

interface ClickHandler {
    fun handleClick(view: View, position: Int)
}