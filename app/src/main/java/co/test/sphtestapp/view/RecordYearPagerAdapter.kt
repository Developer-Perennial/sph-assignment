package co.test.sphtestapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import co.test.sphtestapp.data.network.response.Record

class RecordYearPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val yearData: ArrayList<String>,
    private val datastoreData: ArrayList<Record>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment = YearDetailsFragment.newInstance(yearData[position], datastoreData)

    override fun getItemCount() = yearData.size
}