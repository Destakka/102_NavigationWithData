package com.example.pam_act5.ui

import androidx.lifecycle.ViewModel
import com.example.pam_act5.data.OrderUIState
import com.example.pam_act5.ui.theme.HARGA_PER_CUP
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderViewModel : ViewModel() {
    private val _stateUI = MutableStateFlow(OrderUIState())
    val stateUI: StateFlow<OrderUIState> = _stateUI.asStateFlow()

    fun setJumlah(jmlEsJumbo:Int){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                jumlah = jmlEsJumbo,
                harga = hitungHarga(jumlah = jmlEsJumbo)
            )
        }
    }

    fun setRasa(rasaPilihan: String){
        _stateUI.update { stateSaatIni ->
            stateSaatIni.copy(
                rasa = rasaPilihan
            )
        }
    }

    fun resetOrder(){
        _stateUI.value = OrderUIState()
    }

    private fun hitungHarga(
        jumlah: Int = _stateUI.value.jumlah,
    ) : String {
        val kalkulasiHarga = jumlah * HARGA_PER_CUP

        return NumberFormat.getNumberInstance().format(kalkulasiHarga)
    }

    fun setContact(listData: MutableList<String>) {
        _stateUI.update { stateSaatIni -> stateSaatIni.copy(
            nama = listData[0],
            alamat = listData[1],
            noTelp = listData[2]
        )}
    }

}