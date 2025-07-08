package com.example.carbon_calc_app

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val carbonCalcTextView = view.findViewById<TextView>(R.id.carbonCalcText)
        val paint = carbonCalcTextView.paint
        val width = paint.measureText(carbonCalcTextView.text.toString())
        val shader = LinearGradient(
            0f, 0f, width, 0f,
            intArrayOf(
                Color.parseColor("#4647ED"),
                Color.parseColor("#1E1E1E")
            ),
            null,
            Shader.TileMode.CLAMP
        )
        carbonCalcTextView.paint.shader = shader

        val numAcs = view.findViewById<EditText>(R.id.electricity_1)
        val acHrs = view.findViewById<EditText>(R.id.electricity_2)
        val numFans = view.findViewById<EditText>(R.id.electricity_3)
        val fanHrs = view.findViewById<EditText>(R.id.electricity_4)
        val numLights = view.findViewById<EditText>(R.id.electricity_5)
        val lightHrs = view.findViewById<EditText>(R.id.electricity_6)
        val tvHrs = view.findViewById<EditText>(R.id.electricity_7)
        val wifiHrs = view.findViewById<EditText>(R.id.electricity_8)
        val carKMs = view.findViewById<EditText>(R.id.transport_1)
        val numRiders = view.findViewById<EditText>(R.id.transport_2)
        val schoolBusKMsPerDay = view.findViewById<EditText>(R.id.transport_3)
        val bikeOrScooterKMsPerDay = view.findViewById<EditText>(R.id.transport_4)
        val numPlants = view.findViewById<EditText>(R.id.plants_1)

        val calculateButton = view.findViewById<Button>(R.id.calulateBtn)
        calculateButton.setOnClickListener {
            calculateFootprint(
                acHrs, numAcs, numFans, fanHrs, numLights,
                lightHrs, tvHrs, wifiHrs, carKMs, numRiders,
                schoolBusKMsPerDay, bikeOrScooterKMsPerDay, numPlants
            )
        }
        return view
    }

    private fun calculateFootprint(
        acHrs: EditText, numAcs: EditText, numFans: EditText,
        fanHrs: EditText, numLights: EditText, lightHrs: EditText,
        tvHrs: EditText, wifiHrs: EditText, carKMs: EditText,
        numRiders: EditText, schoolBusKMsPerDay: EditText,
        bikeOrScooterKMsPerDay: EditText, numPlants: EditText
    ){
        val acFactor = 1.5
        val fanFactor = 0.075
        val lightFactor = 0.05
        val tvFactor = 0.1
        val wifiFactor = 0.02
        val carFactor = 0.21
        val schoolBusFactor = 0.05
        val bikeFactor = 0.1
        val plantOffsetFactor=0.05

        val acHours = parseInput(acHrs)
        val fanCount = parseInput(numFans).toInt()
        val fanHours = parseInput(fanHrs)
        val lightCount = parseInput(numLights).toInt()
        val lightHours = parseInput(lightHrs)
        val tvHours = parseInput(tvHrs)
        val wifiHours = parseInput(wifiHrs)
        val carKm = parseInput(carKMs)
        val carRiders = parseInput(numRiders).toInt().takeIf { it > 0 } ?: 1 // Default to 1 if 0
        val schoolBusKm = parseInput(schoolBusKMsPerDay)
        val bikeKm = parseInput(bikeOrScooterKMsPerDay)
        val plantCount = parseInput(numPlants)

        val acFootprint = acHours * acFactor
        val fanFootprint = fanCount * fanHours * fanFactor
        val lightFootprint = lightCount * lightHours * lightFactor
        val tvFootprint = tvHours * tvFactor
        val wifiFootprint = wifiHours * wifiFactor

        val carFootprint = if (carRiders > 0) {
            carKm * carFactor / carRiders
        } else {
            0.0
        }

        val schoolBusFootprint = schoolBusKm * schoolBusFactor
        val bikeFootprint = bikeKm * bikeFactor
        val plantOffset = plantCount * plantOffsetFactor

        val totalDailyFootprint = acFootprint + fanFootprint + lightFootprint + tvFootprint + wifiFootprint + schoolBusFootprint + bikeFootprint - plantOffset
        val totalMonthlyFootprint = totalDailyFootprint*30
        val totalYearlyFootprint = totalDailyFootprint*365
        Toast.makeText(context, "Daily Footprint: $totalDailyFootprint kg CO2\n" +
                "Monthly Footprint: $totalMonthlyFootprint kg CO2\n" +
                "Yearly Footprint: $totalYearlyFootprint kg CO2", Toast.LENGTH_LONG).show()

        val bundle = Bundle().apply {
            putDouble("daily", totalDailyFootprint)
            putDouble("monthly", totalMonthlyFootprint)
            putDouble("yearly", totalYearlyFootprint)
        }
        val plotFragment = PlotFragment().apply{ arguments = bundle }
        parentFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, plotFragment)
            .addToBackStack(null)
            .commit()
    }
    private fun parseInput(editText : EditText):Double {
        return editText.text.toString().toDoubleOrNull() ?: 0.0
    }


}