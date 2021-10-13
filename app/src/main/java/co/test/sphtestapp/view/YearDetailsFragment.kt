package co.test.sphtestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import co.test.sphtestapp.R
import co.test.sphtestapp.common.Constants
import co.test.sphtestapp.common.EventObserver
import co.test.sphtestapp.data.network.Status
import co.test.sphtestapp.data.network.response.Record
import co.test.sphtestapp.databinding.FragmentYearDetailsBinding
import com.google.android.material.snackbar.Snackbar

class YearDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentYearDetailsBinding
    private val binding get() = _binding

    lateinit var yearDetailsViewModel: YearDetailsViewModel

    private lateinit var year: String
    private var datastoreData = arrayListOf<Record>()

    private val quarterWiseVolumeData = arrayListOf<Record>()

    companion object {
        fun newInstance(year: String, datastoreData: ArrayList<Record>): YearDetailsFragment {
            val datastorePagerFragment = YearDetailsFragment()
            val bundle = Bundle()
            bundle.putString(Constants.IntentKeys.POSITION, year)
            bundle.putParcelableArrayList(Constants.IntentKeys.DATASTORE_DATA, datastoreData)
            datastorePagerFragment.arguments = bundle
            return datastorePagerFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        year = requireArguments().getString(Constants.IntentKeys.POSITION, "")
        datastoreData = requireArguments().getParcelableArrayList<Record>(Constants.IntentKeys.DATASTORE_DATA) as ArrayList<Record>
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::_binding.isInitialized) {
            yearDetailsViewModel = ViewModelProvider(requireActivity()).get(YearDetailsViewModel::class.java)

            _binding = FragmentYearDetailsBinding.inflate(inflater, container, false)

            setUpObservers()
            setUpList()
        }

        return binding.root
    }

    private fun setUpList() {
        val rvAdapter = YearDetailsListAdapter(quarterWiseVolumeData)
        binding.rvYearDetailList.adapter = rvAdapter
    }

    private fun setUpObservers() {
        yearDetailsViewModel.quarterWiseData.observe(
                viewLifecycleOwner,
                EventObserver { recordAPIDataList ->
                    when (recordAPIDataList.status) {
                        Status.SUCCESS -> {
                            recordAPIDataList.data?.let { loadAdapter(it) }
                        }
                        Status.ERROR -> {
                            Snackbar.make(binding.root, getString(R.string.issue_while_loading_data), Snackbar.LENGTH_LONG).show()
                        }
                        Status.LOADING -> {}
                    }
                })
        yearDetailsViewModel.filterData(year, datastoreData)
    }

    private fun loadAdapter(quarterWiseData: List<Record>) {
        quarterWiseVolumeData.clear()
        quarterWiseVolumeData.addAll(quarterWiseData)
        binding.rvYearDetailList.adapter?.notifyDataSetChanged()
    }
}