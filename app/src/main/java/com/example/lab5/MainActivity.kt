package com.example.lab5

//noinspection SuspiciousImport
import android.R

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import com.example.lab5.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sizes = listOf("9x13", "10x15", "11x15", "13x18", "15x20", "15x21", "15x22", "15x23", "18x24", "20x30", "21x30", "23x30", "30x30", "30x40", "30x42", "30x45", "30x60", "30x90")
    private val paperTypes = listOf("Матовая", "Глянцевая", "Металлик", "Шелк")

    private val basePrices = mapOf(
        "9x13" to 50.0,
        "10x15" to 60.0,
        "11x15" to 70.0,
        "13x18" to 80.0,
        "15x20" to 90.0,
        "15x21" to 95.0,
        "15x22" to 100.0,
        "15x23" to 105.0,
        "18x24" to 110.0,
        "20x30" to 120.0,
        "21x30" to 125.0,
        "23x30" to 130.0,
        "30x30" to 140.0,
        "30x40" to 150.0,
        "30x42" to 160.0,
        "30x45" to 170.0,
        "30x60" to 180.0,
        "30x90" to 190.0
    )

    private val paperTypeMultipliers = mapOf(
        "Матовая" to 1.0,
        "Глянцевая" to 1.2,
        "Металлик" to 1.5,
        "Шелк" to 1.3
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sizeAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, sizes)
        sizeAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.sizeSpinner.adapter = sizeAdapter

        val paperTypeAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, paperTypes)
        paperTypeAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.paperTypeSpinner.adapter = paperTypeAdapter

        binding.calculateButton.setOnClickListener {
            val quantity = binding.quantityEditText.text.toString().toIntOrNull()
            val size = binding.sizeSpinner.selectedItem.toString()
            val paperType = binding.paperTypeSpinner.selectedItem.toString()

            if (quantity != null) {
                calculatePrice(quantity, size, paperType)
            }
        }
    }

    private fun calculatePrice(quantity: Int, size: String, paperType: String) {
        val basePrice = basePrices[size] ?: 0.0
        val paperTypeMultiplier = paperTypeMultipliers[paperType] ?: 1.0

        val result = basePrice * paperTypeMultiplier * quantity
        val roundedResult = String.format("%.2f", result)

        binding.resultTextView.text = "Стоимость: $roundedResult рублей"
        binding.resultTextView.visibility = View.VISIBLE
    }
}