package com.hasandeniz.covidtracking.util

import android.content.Context
import com.hasandeniz.covidtracking.R
import com.hasandeniz.covidtracking.domain.model.CountryDetails
import com.hasandeniz.covidtracking.domain.model.History
import java.text.SimpleDateFormat
import java.util.Locale

class Utils {

    fun generateShareMessageFromHistory(history: History, context: Context): String {
        val resources = context.resources
        return resources.getString(R.string.country_label, history.country) +
                "${
                    resources.getString(
                        R.string.population_label,
                        history.population.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.new_cases_label,
                        history.cases.new
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.active_cases_label,
                        history.cases.active.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.critical_cases_label,
                        history.cases.critical.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.recovered_cases_label,
                        history.cases.recovered.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.total_cases_label,
                        history.cases.total.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.total_tests_label,
                        history.tests.total.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.new_deaths_label,
                        history.deaths.new
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.total_deaths_label,
                        history.deaths.total.toString()
                    )
                }."
    }

    fun generateShareMessageFromCountryDetails(
        countryDetails: CountryDetails,
        context: Context
    ): String {
        val resources = context.resources
        return resources.getString(R.string.country_label, countryDetails.name) +
                "${
                    resources.getString(
                        R.string.population_label,
                        countryDetails.population.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.new_cases_label,
                        countryDetails.cases.new
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.active_cases_label,
                        countryDetails.cases.active.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.critical_cases_label,
                        countryDetails.cases.critical.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.recovered_cases_label,
                        countryDetails.cases.recovered.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.total_cases_label,
                        countryDetails.cases.total.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.total_tests_label,
                        countryDetails.tests.total.toString()
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.new_deaths_label,
                        countryDetails.deaths.new
                    )
                }, " +
                "${
                    resources.getString(
                        R.string.total_deaths_label,
                        countryDetails.deaths.total.toString()
                    )
                }."
    }

    fun formatDate(inputDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX", Locale.getDefault())
        val outputFormat = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())

        return try {
            val date = inputFormat.parse(inputDate)
            outputFormat.format(date!!)
        } catch (e: Exception) {
            e.printStackTrace()
            inputDate
        }
    }
}