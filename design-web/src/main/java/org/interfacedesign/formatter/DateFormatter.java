package org.interfacedesign.formatter;

import org.slf4j.Logger;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 002136 on 2014/12/10.
 */
public class DateFormatter extends org.springframework.format.datetime.DateFormatter {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DateFormatter.class);

    @Override
    public Date parse(String text, Locale locale) throws ParseException {
        try{
            if(isNumber(text) ){
                return new Date(Long.valueOf(text));
            }
        }catch (Exception e){
            logger.error("日期格式转换错误", e);
        }
        return getDateFormat(locale).parse(text);
    }

    public static boolean isNumber(String numberString) {
        try {
            Double.valueOf(numberString);
            return true;
        } catch (Exception var2) {
            return false;
        }
    }
}
