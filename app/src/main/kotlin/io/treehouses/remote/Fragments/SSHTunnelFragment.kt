package io.treehouses.remote.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import io.treehouses.remote.Views.TunnelViewPager
import io.treehouses.remote.adapter.TunnelPageAdapter
import io.treehouses.remote.bases.BaseFragment
import io.treehouses.remote.callback.ServicesListener
import io.treehouses.remote.databinding.ActivitySshTunnelFragmentBinding
import io.treehouses.remote.pojo.ServiceInfo
import io.treehouses.remote.utils.logD

class SSHTunnelFragment : BaseFragment(), ServicesListener, OnItemSelectedListener, OnPageChangeListener {

    private var tabLayout: TabLayout? = null
    private var tunnelView: TunnelViewPager? = null
    private var tunnelPageAdapter: TunnelPageAdapter? = null
    private lateinit var bind: ActivitySshTunnelFragmentBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bind = ActivitySshTunnelFragmentBinding.inflate(inflater, container, false)
        tabLayout = bind.tabLayout
        tabLayout!!.setTabGravity(TabLayout.GRAVITY_FILL)
        tabLayout!!.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                replaceFragment(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        tunnelView = bind.tabViewpager
        tunnelPageAdapter = TunnelPageAdapter(childFragmentManager)
        tunnelView!!.setAdapter(tunnelPageAdapter)
        tunnelView!!.addOnPageChangeListener(this)
        return bind.root
    }

    fun replaceFragment(position: Int) {
        logD("dasd $position")
        tunnelView!!.currentItem = position
    }

    //
    fun setTabEnabled(enabled: Boolean) {
        val tabStrip = tabLayout!!.getChildAt(0) as LinearLayout
        tabStrip.isEnabled = enabled
        for (i in 0 until tabStrip.childCount) {
            tabStrip.getChildAt(i).isClickable = enabled
        }
    }

    override fun onClick(s: ServiceInfo?) {
        tabLayout!!.getTabAt(1)!!.select()
        replaceFragment(1)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        tabLayout!!.setScrollPosition(position, 0f, true)
    }

    override fun onPageSelected(position: Int) {
    }

    override fun onPageScrollStateChanged(state: Int) {}
}