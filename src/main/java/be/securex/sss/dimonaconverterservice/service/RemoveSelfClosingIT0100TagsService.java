package be.securex.sss.dimonaconverterservice.service;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 6060 on 11/07/2017.
 */
@Component
public class RemoveSelfClosingIT0100TagsService {
//    private static final Pattern pattern = Pattern.compile("\\s*<\\w+/>");
    private static final Pattern pattern = Pattern.compile("\\s*<IT0100/>");

    public String perform(String xml) {
        Matcher matcher1 = pattern.matcher(xml);
        return matcher1.replaceAll("");
    }
}
