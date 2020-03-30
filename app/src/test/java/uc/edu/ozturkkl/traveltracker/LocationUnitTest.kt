package uc.edu.ozturkkl.traveltracker

import org.junit.Test
import org.junit.Assert.*
import uc.edu.ozturkkl.traveltracker.ui.main.MainViewModel

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class LocationUnitTest {

    lateinit var mvm: MainViewModel

    //this test will fail because I haven't added the API key directly into the code for security reasons
//    @Test
//    fun canFindAddress() {
//        mvm = MainViewModel()
//        mvm.loadPredictions("2600 Clifton Avenue");
//        assertTrue(mvm.locations.contains("2600 Clifton Avenue, Cincinnati, OH, USA"))
//    }
}