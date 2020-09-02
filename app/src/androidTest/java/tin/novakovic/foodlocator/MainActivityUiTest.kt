package tin.novakovic.foodlocator

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertNotDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import com.schibsted.spain.barista.interaction.BaristaEditTextInteractions.writeTo
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tin.novakovic.foodlocator.ui.MainActivity

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    @Rule
    @JvmField
    var mainActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun clickSearchButton_emptySearchBar_displayError() {
        clickOn(R.id.search_button)
        assertDisplayed("PostCode Not Recognised")
        assertNotDisplayed(R.id.location_button);
    }

    @Test
    fun enterPostCode_clickSearchButton_displayRecyclerView() {
        writeTo(R.id.search_bar, "sw8")
        clickOn(R.id.search_button)
        assertDisplayed(R.id.recycler_view)
        assertNotDisplayed(R.id.location_button)
    }

    @Test
    fun enterInvalidPostCode_clickSearchButton_displayRecyclerView() {
        writeTo(R.id.search_bar, "sw82")
        clickOn(R.id.search_button)
        assertDisplayed("PostCode/Location Not Recognised")
        assertNotDisplayed(R.id.location_button)
    }

    // Test passes only if location is pre-enabled on the device
    @Test
    fun clickLocationButton_displayRecyclerView() {
        clickOn(R.id.location_button)
        assertDisplayed(R.id.recycler_view)
        assertNotDisplayed(R.id.location_button)
    }

}
