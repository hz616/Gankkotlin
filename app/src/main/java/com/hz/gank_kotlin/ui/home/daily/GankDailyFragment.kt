package com.hz.gank_kotlin.ui.home.daily

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hz.gank_kotlin.R
import com.hz.gank_kotlin.data.Gank
import com.hz.gank_kotlin.data.ui.GankItem
import com.hz.gank_kotlin.databinding.FragmentGankBinding
import com.hz.gank_kotlin.ui.adapter.GankDailyAdapter
import com.hz.gank_kotlin.ui.common.WebActivity
import com.hz.gank_kotlin.ui.home.MainActivity
import kotlinx.android.synthetic.main.recycler_item_gank_data.view.*

class GankDailyFragment : Fragment() {


    private val gankItemList: MutableList<GankItem> = mutableListOf()

    private lateinit var gankDailyAdapter: GankDailyAdapter

    private lateinit var viewBinding: FragmentGankBinding;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_gank, container, false)
        viewBinding.viewModel = (activity as MainActivity).obtainGankDailyViewModel()
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.lifecycleOwner = viewLifecycleOwner
        gankDailyAdapter = object : GankDailyAdapter(gankItemList) {
            override fun addListener(root: View, itemData: Gank, position: Int) {
                super.addListener(root, itemData, position)
                view.item_wrapper?.setOnClickListener {
                    WebActivity.start(activity, itemData.url)
                }

            }
        }
        viewBinding.rvGankDaily.apply {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
            setHasFixedSize(true)
            adapter = gankDailyAdapter
        }
        viewBinding.viewModel?.gankItemList?.observe(viewLifecycleOwner, Observer {
            (viewBinding.rvGankDaily.adapter as GankDailyAdapter).replaceItems(it)
        })
        viewBinding.viewModel?.netWorkError?.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, "网络错误", Toast.LENGTH_SHORT).show()
        })

        if (viewBinding.viewModel?.gankItemList?.value.isNullOrEmpty()) {
            viewBinding?.viewModel?.start()
        }
    }


    companion object {
        const val TAG = "GankDailyFragment"
        @JvmStatic
        fun newInstance() = GankDailyFragment()
    }

}