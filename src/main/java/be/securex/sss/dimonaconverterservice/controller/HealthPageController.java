package be.securex.sss.dimonaconverterservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 6060 on 2/10/2015.
 */
@RestController
@PropertySource("classpath:version.properties")
public class HealthPageController {
    @Value("${version}")
    private String version;

    @RequestMapping(value = "/public/HealthPage", method = RequestMethod.GET)
    public Map<String,String> sayHello() {
        return new HashMap<String, String>(){{
            put("status","succeeded");
            put("version",version);
        }};
    }
}
