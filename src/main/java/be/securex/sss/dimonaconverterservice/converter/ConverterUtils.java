package be.securex.sss.dimonaconverterservice.converter;

import org.apache.commons.lang3.StringUtils;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Created by 8565 on 2/11/2015.
 */
public class ConverterUtils {

    public static String toDdmmyyyyString(XMLGregorianCalendar calendar) {
        if (calendar == null) return null;
        return new SimpleDateFormat("dd/MM/yyyy").format(calendar.toGregorianCalendar().getTime());
    }

    public static String toHhmmString(XMLGregorianCalendar calendar) {
        if (calendar == null) return null;
        return new SimpleDateFormat("HHmm").format(calendar.toGregorianCalendar().getTime());
    }

    public static String toYyyymmddString(XMLGregorianCalendar calendar) {
        if (calendar == null) return null;
        return new SimpleDateFormat("yyyy-MM-dd").format(calendar.toGregorianCalendar().getTime());
    }

    public static String toDecimalHour(Duration duration) {
        //In case that XML duration contains hours => keep them and we suppose that minutes will be < 60
        if (duration.getHours() > 0) {
            BigDecimal minutes = new BigDecimal((double) duration.getMinutes() / 6 * 10);
            minutes = minutes.setScale(0, BigDecimal.ROUND_HALF_UP);
            int hours = duration.getHours();
            return hours + "," + minutes.toString();
        } else {
            //XML duration minutes may be > 60 => calculate overflow for hours
            //for hireapp this scenario will always be used
            double minutes60th = (double) duration.getMinutes();
            double hours = minutes60th / 60;
            BigDecimal hoursBigDecimal = new BigDecimal(hours).setScale(0, BigDecimal.ROUND_DOWN);
            double minutes = minutes60th % 60;
            BigDecimal minutesBigDecimal = new BigDecimal(
                    minutes / 6 * 10);
            minutesBigDecimal = minutesBigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP);
            return hoursBigDecimal.toString() + "," + minutesBigDecimal.toString();
        }
    }

    public static String departmentNumber4Pos(String departmentNumber) {
        if ("00".equals(departmentNumber)) {
            return "0000";
        }
        return StringUtils.prependIfMissing(departmentNumber, "00");
    }

}
