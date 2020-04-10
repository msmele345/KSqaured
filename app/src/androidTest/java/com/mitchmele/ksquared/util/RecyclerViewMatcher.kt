package com.mitchmele.ksquared.util


import android.content.res.Resources
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


class RecyclerViewMatcher(private val recyclerViewId: Int) {

    fun atPosition(position: Int): Matcher<View> {
        return atPositionOnView(position, -1)
    }

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            internal var resources: Resources? = null
            internal var childView: View? = null

            override fun describeTo(description: Description) {
                var idDescription = recyclerViewId.toString()
                if (this.resources != null) {
                    idDescription = try {
                        this.resources!!.getResourceName(recyclerViewId)
                    } catch (var4: Resources.NotFoundException) {
                        String.format(
                            "%s (resource name not found)",
                            *arrayOf<Any>(Integer.valueOf(recyclerViewId))
                        )
                    }

                }

                description.appendText("with id: $idDescription")
            }

            public override fun matchesSafely(view: View): Boolean {

                this.resources = view.resources

                if (childView == null) {
                    val recyclerView =
                        view.rootView?.findViewById<View>(recyclerViewId) as? RecyclerView
                            ?: return false
                    if (recyclerView.id == recyclerViewId) {
                        childView =
                            recyclerView.findViewHolderForAdapterPosition(position)!!.itemView
                    } else {
                        return false
                    }
                }

                return if (targetViewId == -1) {
                    view === childView
                } else {
                    val targetView = childView!!.findViewById<View>(targetViewId)
                    view === targetView
                }
            }
        }
    }

    companion object {
        fun contains(viewId: Int, atPosition: Int, withText: String): Matcher<View> =
            object : TypeSafeMatcher<View>() {
                override fun describeTo(description: Description?) {
                    description?.appendText("Expected RecyclerView's child at position $atPosition to contain a TextView with id $viewId and text $withText")
                }

                override fun matchesSafely(item: View?): Boolean {
                    return (item as? RecyclerView)
                        ?.findViewHolderForAdapterPosition(atPosition)
                        ?.itemView
                        ?.findViewById<TextView>(viewId)
                        ?.text
                        ?.equals(withText) ?: false
                }

            }
    }
}
