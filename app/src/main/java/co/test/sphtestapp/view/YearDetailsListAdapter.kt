package co.test.sphtestapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.test.sphtestapp.data.network.response.Record
import co.test.sphtestapp.databinding.ItemYearDetailsBinding

class YearDetailsListAdapter(
    private val quarterWiseVolumeData: ArrayList<Record>) : RecyclerView.Adapter<YearDetailsListAdapter.YearDetailsListHolder>() {

    inner class YearDetailsListHolder(private val binding: ItemYearDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(record: Record) {
            binding.record = record
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YearDetailsListHolder {
        val itemBinding: ItemYearDetailsBinding =
            ItemYearDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return YearDetailsListHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: YearDetailsListHolder, position: Int) {
        holder.bind(quarterWiseVolumeData[position])
    }

    override fun getItemCount(): Int {
        return quarterWiseVolumeData.size
    }

    fun updateVolumeList(updatedQuarterWiseVolumeData: List<Record>) {
        quarterWiseVolumeData.clear()
        quarterWiseVolumeData.addAll(updatedQuarterWiseVolumeData)
        notifyDataSetChanged()
    }
}