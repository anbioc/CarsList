package com.sevenpeakssoftware.amirnaghavi.data.remote

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.sevenpeakssoftware.amirnaghavi.base.domain.Answer
import com.sevenpeakssoftware.amirnaghavi.base.CarsParam
import com.sevenpeakssoftware.amirnaghavi.base.Mapper
import com.sevenpeakssoftware.amirnaghavi.data.CarsAPI
import com.sevenpeakssoftware.amirnaghavi.data.dto.CarDTO
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
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

    @InjectMocks
    lateinit var subject: RemoteCarsDataSource

    lateinit var observer: TestObserver<Answer<List<CarEntity>>>

    @Test
    fun `givenApiDataAvailable and givenDataIsMapped whenOnRead thenResultIsAvailable`() {
        givenApiDataAvailable()
        givenDataIsMapped()
        whenOnRead()
        thenResultIsAvailable()
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