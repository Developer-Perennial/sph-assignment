package co.test.sphtestapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import co.test.sphtestapp.data.network.response.Record

open class RecordYearPagerViewModel
@ViewModelInject constructor() : ViewModel() {

    fun filterData(year: String, arrayList: List<Record>): List<Record> {
        return arrayList.filter { it.year == year }
    }
}