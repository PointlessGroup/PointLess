package pointlessgroup.pointless.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import pointlessgroup.pointless.R
import pointlessgroup.pointless.databinding.FragmentSettingsBinding
import pointlessgroup.pointless.location.LocationApiLifecycleObserver

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding
    private lateinit var locationApi: LocationApiLifecycleObserver

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.fragment_settings, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationApi = LocationApiLifecycleObserver(context)
        lifecycle.addObserver(locationApi)
    }

    override fun onResume() {
        super.onResume()
        locationApi.addGeoFence()
    }
}