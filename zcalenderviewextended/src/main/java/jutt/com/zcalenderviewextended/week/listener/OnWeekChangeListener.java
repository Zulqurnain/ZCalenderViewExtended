package jutt.com.zcalenderviewextended.week.listener;

import org.joda.time.DateTime;

public interface OnWeekChangeListener {

    void onWeekChange(DateTime firstDayOfTheWeek, boolean forward);
}
