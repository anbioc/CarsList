package com.multithread.car.data.local.fake

import com.multithread.car.base.CarsParam
import com.multithread.car.base.ObservableReadable
import com.multithread.car.base.domain.Answer
import com.multithread.car.base.domain.toSuccessAnswer
import com.multithread.car.domain.entity.CarContentEntity
import com.multithread.car.domain.entity.CarEntity
import io.reactivex.Observable
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * Provides fake DTO objects to feed domain layer, independent from any API end point.
 */
class FakeDataHelper @Inject constructor(): ObservableReadable<List<CarEntity>, CarsParam>() {
    override fun read(param: CarsParam): Observable<Answer<List<CarEntity>>> =
        Observable.just(createCarList().toSuccessAnswer()).delay(2000, TimeUnit.MILLISECONDS)


    private fun createCarList(): List<CarEntity> = mutableListOf<CarEntity>().apply {
        add(
            CarEntity(
                changed = 1534311497,
                content = createContent(
                    "text",
                    "Q7",
                    "The Audi Q7 is masculine, yet exudes lightness. Inside, it offers comfort at the highest level. With even more space for your imagination. The 3.0 TDI engine accelerates this powerhouse as a five-seater starting at an impressive 6.3 seconds from 0 to 100 km/h."
                ),
                created = 1511968425,
                dateTime = "25.05.2018 14:13",
                id = 1,
                image = "http://multi-thread.com/wp-content/uploads/2021/02/audi_a1_0.jpg",
                ingress = "The Audi Q7 is the result of an ambitious idea: never cease to improve.",
                title = "Q7 - Greatness starts, when you don't stop."
            )
        )

        add(
            CarEntity(
                changed = 1516864387,
                content = createContent(
                    "text",
                    "Q5",
                    "Expressive appearance, powerful drive, and pioneering technology. Your options are just as diverse. Every day anew – with the Audi Q5. Don't leave anything to chance. Whether it comes to comfort, style, or dynamics, the Audi Q5 will win you over from the first moment. "
                ),
                created = 1511968397,
                dateTime = "29.11.2017 15:12",
                id = 2,
                image = "http://multi-thread.com/wp-content/uploads/2021/02/audi_q5.jpg",
                ingress = "Don't leave anything to chance. Whether it comes to comfort, style, or dynamics, the Audi Q5 will win you over from the first moment.",
                title = "Q5 - Created for almost any landscape."
            )
        )

        add(
            CarEntity(
                changed = 1516864387,
                content = createContent(
                    "text",
                    "Q2",
                    "Expressive appearance, powerful drive, and pioneering technology. Your options are just as diverse. Every day anew – with the Audi Q5. Don't leave anything to chance. Whether it comes to comfort, style, or dynamics, the Audi Q5 will win you over from the first moment. "
                ),
                created = 1511968497,
                dateTime = "29.11.2017 12:12",
                id = 3,
                image = "http://multi-thread.com/wp-content/uploads/2021/02/audi_q2.jpg",
                ingress = "A car that keeps up with your demands – the Audi Q2. Powerful and agile. ",
                title = "Q2 - En route to myself"
            )
        )

        add(
            CarEntity(
                changed = 1516864388,
                content = createContent(
                    "text",
                    "A7",
                    "Distinctive, unmistakable, A7. "
                ),
                created = 1511968497,
                dateTime = "29.11.2017 15:09",
                id = 4,
                image = "http://multi-thread.com/wp-content/uploads/2021/02/audi_a7.jpg",
                ingress = "Distinctive, unmistakable, A7. ",
                title = "A7 - Four rings. A clear line."
            )
        )

        add(
            CarEntity(
                changed = 1516864388,
                content = createContent(
                    "text",
                    "A6",
                    "And a thrilling wide range of equipment. The Audi A6 Saloon and the Audi A6 Avant combine these values within an extraordinary symbiosis of sportiness and elegance, and open a wide range of possibilities for pioneering mobility. Discover yours. Innovative technologies. Progressive design. "
                ),
                created = 1511968178,
                dateTime = "29.11.2017 15:08",
                id = 5,
                image = "http://multi-thread.com/wp-content/uploads/2021/02/audi_a6_0.jpg",
                ingress = "Innovative technologies. Progressive design. And a thrilling wide range of equipment.",
                title = "A6 - Fascination comes through in many facets."
            )
        )

    }

    private fun createContent(type: String, title: String, desc: String): List<CarContentEntity> =
        mutableListOf<CarContentEntity>().apply {
            add(
                CarContentEntity(type, title, desc)
            )
        }
}