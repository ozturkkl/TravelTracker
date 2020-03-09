package uc.edu.ozturkkl.traveltracker

import org.junit.Test
import org.junit.Assert.*
import uc.edu.ozturkkl.traveltracker.dto.Location
import uc.edu.ozturkkl.traveltracker.ui.main.MainViewModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LocationUnitTest {

    lateinit var mvm: MainViewModel

    @Test
    internal fun locationDTO_maintainsState() {
        val location = Location("5433 Waterfall Hills", "Cincinnati", "Ohio", 45244)
        assertTrue(location.address == "5433 Waterfall Hills")
        assertTrue(location.city == "Cincinnati")
        assertTrue(location.state == "Ohio")
        assertTrue(location.zip == 45244)
    }

    @Test
    internal fun canFindAddress() {
        mvm = MainViewModel()
        mvm.fetchLocations("5433 Waterfall Hills")
        assertTrue(mvm.locations[0].zip == 45244)
    }
}
