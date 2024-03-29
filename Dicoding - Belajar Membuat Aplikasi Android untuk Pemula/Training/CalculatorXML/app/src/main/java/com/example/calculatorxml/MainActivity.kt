package com.example.calculatorxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {

//    Menggunakan lateinit agar dapat mendeklarasikan variable tanpa ada value (nullable)
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate: Button
    private lateinit var tvResult: TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Berfungsi untuk mengambil id dari editText, TextView, dsb
        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)
        btnCalculate.setOnClickListener(this)
        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
//        Hasil dari perhitungan result

//        Untuk menyimpan data disini menggunakan konsep Key-Value,
//        dengan STATE_RESULT sebagai key dan isi dari tvResult sebagai value
//        Fungsi onSaveInstanceState akan dipanggil secara otomatis sebelum sebuah Activity hancur.
//        Di sini kita perlu menambahkan onSaveInstanceState karena ketika orientasi berubah,
//        Activity tersebut akan di-destroy dan memanggil fungsi onCreate lagi,
//        sehingga kita perlu menyimpan nilai hasil perhitungan tersebut supaya data
//        tetap terjaga dan tidak hilang ketika orientasi berubah.

        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
    override fun onClick(view: View?) {
        if (view?.id == R.id.btn_calculate) {

//          Berfungsi untuk mengambil isi dari sebuah edit text dan menggunakan trim untuk menghiraukan spasi bila ada
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()
            var isEmptyFields = false
            if (inputLength.isEmpty()) {
                isEmptyFields = true
                edtLength.error = "Field ini tidak boleh kosong"
            }
            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtWidth.error = "Field ini tidak boleh kosong"
            }
            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                edtHeight.error = "Field ini tidak boleh kosong"
            }
            if (!isEmptyFields) {
                val volume = inputLength.toDouble() * inputWidth.toDouble() * inputHeight.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }

}






