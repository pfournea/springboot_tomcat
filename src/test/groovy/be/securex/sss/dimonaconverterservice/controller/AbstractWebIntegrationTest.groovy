package be.securex.sss.dimonaconverterservice.controller

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
/**
 * Created by 6060 on 7/10/2015.
 */
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractWebIntegrationTest extends Specification {
    @Value("\${local.server.port}")
    def port
    def url

    def setup() {
        url = 'http://localhost:' + port + '/sssservices/dimonaconverterservice'
        innerSetup()
    }
    def innerSetup(){}
}
