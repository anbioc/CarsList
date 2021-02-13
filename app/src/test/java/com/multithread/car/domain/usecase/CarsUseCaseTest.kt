package com.multithread.car.domain.usecase

import com.nhaarman.mockitokotlin2.given
import com.multithread.car.base.*
import com.multithread.car.base.domain.*
import com.multithread.car.domain.entity.CarEntity
import com.multithread.car.domain.repository.CarListRepository
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CarsUseCaseTest {

    private lateinit var testObserver: TestObserver<Answer<List<CarEntity>>>
    private val carsParam = CarsParam()
    private val repositoryStrategy = RepositoryStrategy.OfflineFirst

    private val mockCarEntity: CarEntity = mock(CarEntity::class.java)

    private val mockCarsRepository: CarListRepository = mock(CarListRepository::class.java)

    lateinit var mockScheduler: SchedulerProvider

    private val mockErrorContainer: ErrorContainer = mock(ErrorContainer::class.java)

    private lateinit var subject: CarsUseCase

    private lateinit var result: Answer<List<CarEntity>>

    @Before
    fun setup() {
        initMocks(this)
        result = listOf(mockCarEntity).toSuccessAnswer()
        mockScheduler = TestScheduler()
        subject = CarsUseCase(
                mockCarsRepository,
                mockScheduler,
                mockErrorContainer)
    }

    @Test
    fun `givenRepositoryDataIsAvailable whenOnExecute thenResultIsAvailable`() {
        givenRepositoryDataIsAvailable()
        whenOnExecute()
        thenResultIsAvailable()
    }

    private fun givenRepositoryDataIsAvailable() {
        given(
                mockCarsRepository.getResult(carsParam, repositoryStrategy)
        ).willReturn(result.toObservable())
    }

    private fun whenOnExecute() {
        testObserver = subject.execute(carsParam, repositoryStrategy).test()
    }

    private fun thenResultIsAvailable() = testObserver.assertNoErrors()
            .assertComplete()
            .assertValues(result)

}