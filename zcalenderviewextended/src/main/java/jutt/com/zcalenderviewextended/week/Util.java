package jutt.com.zcalenderviewextended.week;

import org.joda.time.DateTimeConstants;

import java.util.Calendar;

/**
 * Created by sdsol on 30/08/2017.
 */

public class Util {

    /**
     * convert Calender.SUNDAY to DateTimeConstants.SUNDAY
     * @param calenderDay
     * @return
     */
    public static int getDateTimeDayFromCalenderDay(int calenderDay){
        switch (calenderDay){
            case Calendar.SUNDAY:
                return DateTimeConstants.SUNDAY;
            case Calendar.MONDAY:
                return DateTimeConstants.MONDAY;
            case Calendar.TUESDAY:
                return DateTimeConstants.TUESDAY;
            case Calendar.WEDNESDAY:
                return DateTimeConstants.WEDNESDAY;
            case Calendar.THURSDAY:
                return DateTimeConstants.THURSDAY;
            case Calendar.FRIDAY:
                return DateTimeConstants.FRIDAY;
            case Calendar.SATURDAY:
                return DateTimeConstants.SATURDAY;
            default:
                return -1;
        }
    }

    /**
     * convert Calender.SUNDAY to DateTimeConstants.SUNDAY
     * @param calenderDay
     * @return
     */
    public static int getCalenderDayFromDateTimeDay(int calenderDay){
        switch (calenderDay){
            case DateTimeConstants.SUNDAY:
                return Calendar.SUNDAY;
            case DateTimeConstants.MONDAY:
                return Calendar.MONDAY;
            case DateTimeConstants.TUESDAY:
                return Calendar.TUESDAY;
            case DateTimeConstants.WEDNESDAY:
                return Calendar.WEDNESDAY;
            case DateTimeConstants.THURSDAY:
                return Calendar.THURSDAY;
            case DateTimeConstants.FRIDAY:
                return Calendar.FRIDAY;
            case DateTimeConstants.SATURDAY:
                return Calendar.SATURDAY;
            default:
                return -1;
        }
    }
}
