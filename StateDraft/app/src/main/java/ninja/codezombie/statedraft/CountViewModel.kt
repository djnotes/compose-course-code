package ninja.codezombie.statedraft

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountViewModel : ViewModel() {

    private val _count =  MutableLiveData(0)
    val count : LiveData<Int> = _count

    fun onCountChange(change: Int){
        _count.value = if (_count.value!! <= 0 && change == -1) 0 else _count.value!! + change
    }

    private val _name = MutableLiveData("")
    val name : LiveData<String> = _name

    fun onNameChange(newName: String){
        _name.value = newName
    }

}