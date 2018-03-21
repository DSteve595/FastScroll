package com.reddit.indicatorfastscroll.sample.examples

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.reddit.indicatorfastscroll.sample.ListItem
import com.reddit.indicatorfastscroll.sample.R
import com.reddit.indicatorfastscroll.sample.SAMPLE_DATA_TEXT
import com.reddit.indicatorfastscroll.sample.SampleAdapter
import com.reddit.recyclerfastscroll.FastScrollItemIndicator
import com.reddit.recyclerfastscroll.FastScrollThumbView
import com.reddit.recyclerfastscroll.FastScrollView

class JustTextFragment : Fragment() {

  private lateinit var recyclerView: RecyclerView
  private lateinit var fastScrollView: FastScrollView
  private lateinit var fastScrollThumbView: FastScrollThumbView

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View {
    val view = inflater.inflate(R.layout.sample_basic, container, false)

    val data = SAMPLE_DATA_TEXT

    recyclerView = view.findViewById(R.id.sample_basic_recyclerview)
    recyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = SampleAdapter(data)
    }

    fastScrollView = view.findViewById(R.id.sample_basic_fastscroll)
    fastScrollView.apply {
      setupWithRecyclerView(
          recyclerView,
          { position ->
            data[position]
                .takeIf(ListItem::showInFastScroll)
                ?.let { item ->
                  FastScrollItemIndicator.Text(
                      item
                          .title
                          .substring(0, 1)
                          .toUpperCase()
                  )
                }
          }
      )
    }

    fastScrollThumbView = view.findViewById(R.id.sample_basic_fastscroll_thumb)
    fastScrollThumbView.apply {
      setupWithFastScrollView(fastScrollView)
    }

    return view
  }

}
