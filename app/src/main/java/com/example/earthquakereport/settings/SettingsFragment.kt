package com.example.earthquakereport.settings

import android.content.SharedPreferences
import android.content.SharedPreferences.*
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceFragmentCompat
import com.example.earthquakereport.MainActivity
import com.example.earthquakereport.R
import com.example.earthquakereport.earthFragment.EarthQuakeFragment

class SettingsFragment : PreferenceFragmentCompat(), OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == "magnitude") {
            Log.i(
                "Settings Activity",
                "Preference value was updated to: " + sharedPreferences?.getBoolean(
                    "magnitude",
                    false
                )
            )
            EarthQuakeFragment.refreshLayout = sharedPreferences?.getBoolean("magnitude", false)!!
        }

    }
}
