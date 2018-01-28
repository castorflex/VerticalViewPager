package fr.castorflex.android.verticalviewpager.sample

import android.app.Activity
import android.app.Fragment
import android.app.FragmentManager
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.support.v13.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import java.util.Locale

import fr.castorflex.android.verticalviewpager.VerticalViewPager

class MainActivity : Activity() {

    private lateinit var verticalViewPager: VerticalViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        verticalViewPager = findViewById<View>(R.id.verticalviewpager) as VerticalViewPager

        verticalViewPager.adapter = DummyAdapter(fragmentManager)
        verticalViewPager.pageMargin = resources.getDimensionPixelSize(R.dimen.pagemargin)
        verticalViewPager.setPageMarginDrawable(ColorDrawable(resources.getColor(android.R.color.holo_green_dark)))

        verticalViewPager.setPageTransformer(true) { view, position ->
            val pageWidth = view.width
            val pageHeight = view.height

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.alpha = 0f

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                val scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position))
                val vertMargin = pageHeight * (1 - scaleFactor) / 2
                val horzMargin = pageWidth * (1 - scaleFactor) / 2
                if (position < 0) {
                    view.translationY = vertMargin - horzMargin / 2
                } else {
                    view.translationY = -vertMargin + horzMargin / 2
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.scaleX = scaleFactor
                view.scaleY = scaleFactor

                // Fade the page relative to its size.
                view.alpha = MIN_ALPHA + (scaleFactor - MIN_SCALE) / (1 - MIN_SCALE) * (1 - MIN_ALPHA)

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.alpha = 0f
            }
        }
    }

    inner class DummyAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }

        override fun getPageTitle(position: Int): CharSequence? {
            Locale.getDefault()
            when (position) {
                0 -> return "PAGE 1"
                1 -> return "PAGE 2"
                2 -> return "PAGE 3"
            }
            return null
        }

    }

    override fun onResume() {
        super.onResume()
        Handler().postDelayed({
            verticalViewPager.setCurrentItem(3,1500)
        }, 3000)
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_layout, container, false)
            val textView = rootView.findViewById<View>(R.id.textview) as TextView
            textView.text = Integer.toString(if (arguments != null) arguments.getInt(ARG_SECTION_NUMBER,1) else 1)
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }


    }

    companion object {

        private val MIN_SCALE = 0.75f
        private val MIN_ALPHA = 0.75f
    }

}
