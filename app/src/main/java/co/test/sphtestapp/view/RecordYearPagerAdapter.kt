package co.test.sphtestapp.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecordYearPagerAdapter(
    fragmentActivity: FragmentActivity,
    private val yearData: ArrayList<String>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment = YearDetailsFragment.newInstance(yearData[position])

    override fun getItemCount() = yearData.size


    // Returns the page title for the top indicator
    fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }
}