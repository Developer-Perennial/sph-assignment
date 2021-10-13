package co.test.sphtestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
    private val quarterWiseVolumeData = arrayListOf<Record>()

    companion object {
        fun newInstance(year: String): YearDetailsFragment {
            val datastorePagerFragment = YearDetailsFragment()
            val bundle = Bundle()
            bundle.putString(Constants.IntentKeys.POSITION, year)
            datastorePagerFragment.arguments = bundle
            return datastorePagerFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        year = requireArguments().getString(Constants.IntentKeys.POSITION, "")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (!::_binding.isInitialized) {
            yearDetailsViewModel = ViewModelProvider(requireActivity()).get(YearDetailsViewModel::class.java)

            _binding = FragmentYearDetailsBinding.inflate(inflater, container, false)

            setUpList()
            setUpObservers()
        }

        return binding.root
    }

    private fun setUpList() {
        val rvAdapter = YearDetailsListAdapter(quarterWiseVolumeData)
        binding.rvYearDetailList.adapter = rvAdapter
    }

    private fun setUpObservers() {
        yearDetailsViewModel.datastoreYearDbData.observe(
            viewLifecycleOwner,
             { recordYearDBDataList ->
                        if (recordYearDBDataList!!.isEmpty()) {
                            Snackbar.make(binding.root, getString(R.string.issue_while_loading_data), Snackbar.LENGTH_LONG).show()
                        } else {
                            loadAdapter(recordYearDBDataList)
                        }

                }
            )

        println("load fetchDatastoreRecordsDb for $year")
        yearDetailsViewModel.fetchDatastoreRecordsDb(requireArguments().getString(Constants.IntentKeys.POSITION, ""))
    }

    private fun loadAdapter(quarterWiseData: List<Record>) {
        println("load adpter for $year ${quarterWiseData.size}")
        (binding.rvYearDetailList.adapter as YearDetailsListAdapter).updateVolumeList(quarterWiseData)
    }
}