package co.test.sphtestapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import co.test.sphtestapp.R
import co.test.sphtestapp.common.EventObserver
import co.test.sphtestapp.data.network.Status
import co.test.sphtestapp.data.network.Status.*
import co.test.sphtestapp.data.network.response.Record
import co.test.sphtestapp.databinding.FragmentDatastoreListBinding
import com.google.android.material.snackbar.Snackbar

class DatastoreListFragment : Fragment(), ClickHandler  {

    private var _binding: FragmentDatastoreListBinding? = null
    private val binding get() = _binding!!

    lateinit var datastoreListViewModel: DatastoreListViewModel

    private val _yearWiseVolumeData = arrayListOf<Record>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        datastoreListViewModel = ViewModelProvider(requireActivity()).get(DatastoreListViewModel::class.java)

        _binding = FragmentDatastoreListBinding.inflate(inflater, container, false)
        binding.viewModel = datastoreListViewModel

        setUpObservers()
        setUpList()

        datastoreListViewModel.getDatastoreRecords()

        return binding.root
    }

    private fun setUpList() {
        val rvAdapter = DatastoreListAdapter(_yearWiseVolumeData, this)
        binding.rvDatastoreList.adapter = rvAdapter
    }

    private fun setUpObservers() {
        datastoreListViewModel.datastoreResponse.observe(
            viewLifecycleOwner,
            EventObserver { recordAPIDataList ->
                when (recordAPIDataList.status) {
                    SUCCESS -> {
                        loadAdapter(recordAPIDataList.data?.result?.records)
                    }
                    ERROR -> {
                        Snackbar.make(binding.root, getString(R.string.issue_while_loading_data), Snackbar.LENGTH_LONG).show()
                    }
                    LOADING -> {}
                }
            })
    }

    private fun loadAdapter(datastoreList: List<Record>?) {
        _yearWiseVolumeData.addAll(datastoreListViewModel.filterData(datastoreList))
        binding.rvDatastoreList.adapter?.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun handleClick(view: View, position: Int) {
    }
}