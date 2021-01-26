package com.sevenpeakssoftware.amirnaghavi.data.local.data_source

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import com.sevenpeakssoftware.amirnaghavi.base.Answer
import com.sevenpeakssoftware.amirnaghavi.base.TwoWayMapper
import com.sevenpeakssoftware.amirnaghavi.data.local.CarDao
import com.sevenpeakssoftware.amirnaghavi.data.local.data.CarItemLocalEntity
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class CarsLocalDataSourceTest {

    @Mock
    lateinit var mockCarEntity: CarEntity

    @Mock
    lateinit var mockCarLocalEntity: CarItemLocalEntity

    @Mock
    lateinit var mockDao: CarDao

    @Mock
    private lateinit var mockMapper: TwoWayMapper<List<CarEntity>, List<CarItemLocalEntity>>

    @InjectMocks
    private lateinit var subject: CarsLocalDataSource

    private lateinit var testObserver: TestObserver<Answer<List<CarEntity>>>


    @Test
    fun `givenLocalDataIsReady and givenDataIsMappedFromLocalToDomain whenOnRead thenResultIsAvailable`() {
        givenLocalDataIsReady()
        givenDataIsMapperFromLocalToDomain()
        whenOnRead()
        thenResultIsAvailable()
    }

    @Test
    fun `givenGivenDomainDataIsReady givenDataIsMappedFromDomainToLocal whenOnWrite thenDataIsSavedLocally`() {
        givenDataIsMappedFromDomainToLocal()
        whenOnWrite()
        thenDataIsSavedLocally()
    }


    /*
    given
     */
    private fun givenLocalDataIsReady() =
        given(mockDao.getCars()).willReturn(
            Observable.just(listOf(mockCarLocalEntity))
        )


    private fun givenDataIsMapperFromLocalToDomain() =
        given(mockMapper.mapRightToLeft(any())).willReturn(
            listOf(mockCarEntity)
        )


    private fun givenDataIsMappedFromDomainToLocal() =
        given(mockMapper.mapLeftToRight(any())).willReturn(
            listOf(mockCarLocalEntity)
        )

    /*
    when
     */

    private fun whenOnRead() {
        testObserver = subject.read().test()
    }

    private fun whenOnWrite() {
        subject.write(listOf(mockCarEntity))
    }


    /*
    then
     */

    private fun thenResultIsAvailable() = testObserver.assertNoTimeout()
        .assertNoErrors()
        .assertComplete()
        .assertValues(
            Answer.Success(listOf(mockCarEntity))
        )

    private fun thenDataIsSavedLocally() {
        verify(mockDao).insertCars(listOf(mockCarLocalEntity))
        verify(mockMapper).mapLeftToRight(listOf(mockCarEntity))
    }
}