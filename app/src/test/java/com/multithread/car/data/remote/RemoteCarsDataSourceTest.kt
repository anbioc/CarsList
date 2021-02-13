package com.multithread.car.data.remote

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.multithread.car.base.domain.Answer
import com.multithread.car.base.CarsParam
import com.multithread.car.base.Mapper
import com.multithread.car.base.ObservableReadable
import com.multithread.car.base.domain.toSuccessAnswer
import com.multithread.car.data.CarsAPI
import com.multithread.car.data.dto.CarDTO
import com.multithread.car.data.local.fake.FakeDataHelper
import com.multithread.car.domain.entity.CarEntity
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteCarsDataSourceTest {

    @Mock
    lateinit var apiResponse: CarDTO

    @Mock
    lateinit var carEntity: CarEntity

    @Mock
    lateinit var mockApi: CarsAPI

    @Mock
    lateinit var mockMapper: Mapper<CarDTO, List<CarEntity>>

    @Mock
    lateinit var mockFakeDataHelper: ObservableReadable<List<CarEntity>, CarsParam>

    @InjectMocks
    lateinit var subject: RemoteCarsDataSource

    lateinit var observer: TestObserver<Answer<List<CarEntity>>>

    @Test
    fun `givenApiDataAvailable and givenDataIsMapped whenOnRead thenResultIsAvailable`() {
        givenFakeDataIsAvailable()
        whenOnRead()
        thenResultIsAvailable()
    }

    private fun givenFakeDataIsAvailable() {
        given(mockFakeDataHelper.read(any())).willReturn(
            Observable.just(listOf(carEntity).toSuccessAnswer())
        )
    }

    /*
    given
     */
    private fun givenDataIsMapped() {
        given(mockMapper.map(any())).willReturn(listOf(carEntity))
    }

    private fun givenApiDataAvailable() {
        given(mockApi.getCarData()).willReturn(
            Observable.just(apiResponse)
        )
    }

    /*
    when
     */
    private fun whenOnRead() {
        observer = subject.read(CarsParam()).test()
    }

    /*
    then
     */

    private fun thenResultIsAvailable() = observer.assertComplete()
        .assertNoErrors()
        .assertNoTimeout()
        .assertValues(Answer.Success(listOf(carEntity)))

}