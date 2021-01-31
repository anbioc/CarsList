package com.sevenpeakssoftware.amirnaghavi.presentation


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.car.CarState
import com.sevenpeakssoftware.amirnaghavi.presentation.car.CarsViewModel
import com.sevenpeakssoftware.amirnaghavi.presentation.car.GetCarInfoEvent
import io.reactivex.Observable
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CarsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var eventHandlerManager: CompositeEventHandler<CarState, CarsParam>

    @Mock
    lateinit var mockCarEntity: CarEntity

    @InjectMocks
    lateinit var subject: CarsViewModel

    private var historyObserver = HistoryObserver<CarState>()

    private val carParam = CarsParam()
    private val carEvent = GetCarInfoEvent()

    private val initState = CarState(
            data = CarState.Data.Idle,
            baseState = BaseState()
    )

    @Before
    fun setup() {
        subject.stateLiveData.observeForever(historyObserver)
    }

    @Test
    fun `givenUseCaseCarsInfoAvailable whenOnHandleGetCarInfoEvent thenResultIsAvailable`() {
        givenOnGetCarsInfoEvent()
        whenOnHandleGetCarInfoEvent()
        thenResultIsAvailable()
    }

    /*
     given
     */
    private fun givenOnGetCarsInfoEvent() {
        given(eventHandlerManager.handleEvent(any(), any(), any())).willReturn(
                Observable.just(CarState(
                        data = CarState.Data.Cars(listOf(mockCarEntity)),
                        baseState = initState.baseState.noErrorNoLoading()
                ))
        )
    }

    /*
    when
     */
    private fun whenOnHandleGetCarInfoEvent() {
        subject.handleEvent(carEvent, carParam)
    }

    /*
    then
     */
    private fun thenResultIsAvailable() =
            historyObserver.assertNotEmpty()
                    .let {
                        (it.getHistoryItem()).apply {
                            assertThat(data.javaClass).isEqualTo(CarState.Data.Cars::class.java)
                            assertTrue(this.baseState.error.isNotError())
                        }
                    }

}
