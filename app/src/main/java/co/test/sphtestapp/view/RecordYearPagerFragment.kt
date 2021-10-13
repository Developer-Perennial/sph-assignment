package co.test.sphtestapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import co.test.sphtestapp.common.Constants
import co.test.sphtestapp.data.network.response.Record
import co.test.sphtestapp.databinding.FragmentRecordYearPagerBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class RecordYearPagerFragment : Fragment() {

    private var _binding: FragmentRecordYearPagerBinding? = null
    private val binding get() = _binding!!

    lateinit var recordYearPagerViewModel: RecordYearPagerViewModel

    private var position: Int = 0
    private var yearData = arrayListOf<String>()
    private var datastoreData = arrayListOf<Record>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        recordYearPagerViewModel = ViewModelProvider(requireActivity()).get(RecordYearPagerViewModel::class.java)

        _binding = FragmentRecordYearPagerBinding.inflate(inflater, container, false)

        init()

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        position = requireArguments().getInt(Constants.IntentKeys.POSITION, 0)
        yearData = requireArguments().getStringArrayList(Constants.IntentKeys.YEAR_DATA) as ArrayList<String>
        datastoreData = requireArguments().getParcelableArrayList<Record>(Constants.IntentKeys.DATASTORE_DATA) as ArrayList<Record>
    }

    private fun init() {
        setupTabs(position)
    }

    private fun setupTabs(positionClicked: Int) {
        binding.vpYearData.adapter = RecordYearPagerAdapter(requireActivity(), yearData, datastoreData)
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
        yearData.clear()
    }
}