package com.example.carbon_calc_app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class PlotFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_plot, container, false)
        val daily = arguments?.getDouble("daily", 0.0) ?: 0.0
        val monthly = arguments?.getDouble("daily", 0.0) ?: 0.0
        val yearly = arguments?.getDouble("daily", 0.0) ?: 0.0

        val dailyValue = view.findViewById<TextView>(R.id.dailyValue)
        val monthlyValue = view.findViewById<TextView>(R.id.monthlyValue)
        val yearlyValue = view.findViewById<TextView>(R.id.yearlyValue)

        val messageTxtView = view.findViewById<TextView>(R.id.messageTextView)

        dailyValue.text = String.format("%.2f kg CO2", daily)
        monthlyValue.text = String.format("%.2f kg CO2", monthly)
        yearlyValue.text = String.format("%.2f kg CO2", yearly)

        val message = when {
            daily < 5 -> {
                "Great job! Your carbon footprint is relatively low.\n\n" +
                        "• Keep up the good work!\n" +
                        "• Continue to use energy-efficient appliances.\n" +
                        "• Consider reducing even further by adopting a green lifestyle."
            }
            daily < 15 -> {
                "Your carbon footprint is moderate. Consider ways to reduce it.\n\n" +
                        "• Reduce energy consumption by using energy-efficient appliances.\n" +
                        "• Opt for public transportation or carpool to decrease travel emissions.\n" +
                        "• Plant more trees and reduce waste."
            }
            else -> {
                "Your carbon footprint is high. Please look into reducing your emissions.\n\n" +
                        "• Switch to renewable energy sources like solar or wind power.\n" +
                        "• Reduce car travel and increase use of public transportation or biking.\n" +
                        "• Implement energy-saving practices at home, such as improving insulation and using programmable thermostats."
            }
        }

        messageTxtView.text = message


        return view
    }




}