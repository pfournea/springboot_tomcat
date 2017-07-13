package be.securex.sss.dimonaconverterservice.controller

import spock.lang.Specification

/**
 * Created by 6060 on 2/10/2015.
 */
class HealthPageControllerTest extends Specification {
    HealthPageController healthPageController
    def version = '1.0.0'

    def setup() {
        healthPageController = new HealthPageController()
    }

    def "Test sayHello"() {
        given:
        healthPageController.version = version

        when:
        def result = healthPageController.sayHello()
        then:
        result['status'] == 'succeeded'
        result['version'] == version
    }

}
