package com.sevenpeakssoftware.amirnaghavi.domain.repository

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.base.domain.Answer
import com.sevenpeakssoftware.amirnaghavi.base.domain.RepositoryStrategy
import com.sevenpeakssoftware.amirnaghavi.base.domain.toSuccessAnswer
import com.sevenpeakssoftware.amirnaghavi.data.local.data_source.CarsLocalDataSource
import com.sevenpeakssoftware.amirnaghavi.data.remote.RemoteCarsDataSource
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CarListRepositoryTest {


    private lateinit var testObserver: TestObserver<Answer<List<CarEntity>>>

    @Mock
    lateinit var mockCarEntity: CarEntity

    private val carsParam = CarsParam()
    private var strategy = RepositoryStrategy.OfflineFirst

    @Mock
    private lateinit var mockLocalDataSource: CarsLocalDataSource

    @Mock
    private lateinit var mockRemoteCarsDataSource: RemoteCarsDataSource

    @InjectMocks
    lateinit var subject: CarListRepository


    @Test
    fun `givenOfflineFirstStrategy and givenLocalDataIsAvailable and givenRemoteDataIsAvailable whenOnGetResult thenResultIsAvailable thenLocalDataSourceCallSaveData`() {
        givenLocalDataIsAvailable()
        givenRemoteDataIsAvailable()
        whenOnGetResult()
        thenResultIsAvailable()
        thenLocalDataSourceCallWrite()
    }


    /*
    given
     */
    private fun givenRemoteDataIsAvailable()  {
        given(mockRemoteCarsDataSource.read(carsParam)).willReturn(
            Observable.just(
                listOf(mockCarEntity).toSuccessAnswer()
            )
        )
    }

    private fun givenLocalDataIsAvailable() {
        given(mockLocalDataSource.read(carsParam)).willReturn(
            Observable.just(
                listOf(mockCarEntity).toSuccessAnswer()
            )
        )
    }


    /*
    when
     */
    private fun whenOnGetResult() {
        testObserver = subject.getResult(carsParam, strategy).test()
    }

    /*
    then
     */
    private fun thenResultIsAvailable() = testObserver.assertNoErrors()
        .assertComplete()
        .assertValueCount(2)
        .assertValues(
            listOf(mockCarEntity).toSuccessAnswer(),
            listOf(mockCarEntity).toSuccessAnswer()
        )

    private fun thenLocalDataSourceCallWrite() {
        verify(mockLocalDataSource).write(any())
    }

}