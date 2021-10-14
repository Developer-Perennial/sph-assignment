package co.test.sphtestapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import co.test.sphtestapp.common.Constants
import co.test.sphtestapp.common.Constants.IntentKeys.Companion.YEAR_SELECTED
import co.test.sphtestapp.databinding.FragmentRecordYearPagerBinding
import co.test.sphtestapp.receiver.YearTrackerReceiver
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RecordYearPagerFragment : Fragment() {

    private lateinit var _binding: FragmentRecordYearPagerBinding
    private val binding get() = _binding

    private var position: Int = 0
    private var yearData = arrayListOf<String>()

    private val pageChanged =  object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            sendBroadCast(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRecordYearPagerBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = requireArguments().getInt(Constants.IntentKeys.POSITION, 0)
        yearData = requireArguments().getStringArrayList(Constants.IntentKeys.YEAR_DATA) as ArrayList<String>
    }

    private fun init() {
        setupTabs(position)
    }

    private fun setupTabs(positionClicked: Int) {
        binding.vpYearData.registerOnPageChangeCallback(pageChanged)
        binding.vpYearData.adapter = RecordYearPagerAdapter(requireActivity(), yearData)
        binding.vpYearData.offscreenPageLimit = 3
        binding.vpYearData.setCurrentItem(positionClicked, false)
        binding.tabEntryYear.getTabAt(positionClicked)?.select()

        TabLayoutMediator(binding.tabEntryYear, binding.vpYearData
        ) { tab: TabLayout.Tab, position: Int ->
            tab.text = yearData[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.vpYearData.registerOnPageChangeCallback(pageChanged)
        yearData.clear()
    }

    private fun sendBroadCast(pagePosition: Int) {
        val intent = Intent()
        intent.setClass(requireActivity(), YearTrackerReceiver::class.java)
        intent.putExtra(YEAR_SELECTED, yearData[pagePosition])
        requireActivity().sendBroadcast(intent)
    }

}