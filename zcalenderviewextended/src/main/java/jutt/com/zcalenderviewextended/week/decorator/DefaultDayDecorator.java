package jutt.com.zcalenderviewextended.week.decorator;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DimenRes;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import jutt.com.zcalenderviewextended.R;
import jutt.com.zcalenderviewextended.week.Util;
import jutt.com.zcalenderviewextended.week.WeekCalendar;
import jutt.com.zcalenderviewextended.week.fragment.WeekFragment;

/**
 * Created by gokhan on 7/27/16.
 */
public class DefaultDayDecorator implements DayDecorator {

    private Context context;
    private final int selectedDateColor;
    private final int todayDateColor;
    private int todayDateTextColor;
    private int textColor;
    private int weekDaysTextColor;
    private float textSize;

    public DefaultDayDecorator(Context context,
                               @ColorInt int selectedDateColor,
                               @ColorInt int todayDateColor,
                               @ColorInt int todayDateTextColor,
                               @ColorInt int textColor,
                               @ColorInt int weekDaysTextColor,
                               float textSize) {
        this.context = context;
        this.selectedDateColor = selectedDateColor;
        this.todayDateColor = todayDateColor;
        this.todayDateTextColor = todayDateTextColor;
        this.textColor = textColor;
        this.weekDaysTextColor = weekDaysTextColor;
        this.textSize = textSize;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void decorate(View view, TextView dayTextView,
                         DateTime dateTime, DateTime firstDayOfTheWeek, DateTime selectedDateTime) {
        //DateTime dt = new DateTime();

        Drawable holoCircle = ContextCompat.getDrawable(context, R.drawable.holo_circle);
        Drawable solidCircle = ContextCompat.getDrawable(context, R.drawable.solid_circle);

        solidCircle.setColorFilter(todayDateColor, PorterDuff.Mode.SRC_ATOP);
        holoCircle.setColorFilter(selectedDateColor, PorterDuff.Mode.SRC_ATOP);
        // solidCircle.mutate().setAlpha(200);
        //holoCircle.mutate().setAlpha(200);


        if (firstDayOfTheWeek.getMonthOfYear() < dateTime.getMonthOfYear()
                || firstDayOfTheWeek.getYear() < dateTime.getYear())
            dayTextView.setTextColor(Color.GRAY);

        DateTime calendarStartDate = WeekFragment.CalendarStartDate;



        boolean isWeekDay = dateTime.getDayOfWeek() == DateTimeConstants.SUNDAY ||
                dateTime.getDayOfWeek() == DateTimeConstants.SATURDAY;

        dayTextView.setTypeface(null, Typeface.BOLD);

        dayTextView.setTextColor(textColor);

        if(isWeekDay){
            dayTextView.setTextColor(this.weekDaysTextColor);
        }

        if (selectedDateTime != null) {
            if (selectedDateTime.toLocalDate().equals(dateTime.toLocalDate())) {
//                if (!selectedDateTime.toLocalDate().equals(calendarStartDate.toLocalDate())) {
//
//                }
                solidCircle.setColorFilter(selectedDateColor, PorterDuff.Mode.SRC_ATOP);
                dayTextView.setBackground(solidCircle);
                dayTextView.setTextColor(this.todayDateTextColor);
            }else{
                dayTextView.setBackground(null);
                if(dateTime.toLocalDate().equals(calendarStartDate.toLocalDate())){
                    dayTextView.setBackground(solidCircle);
                    dayTextView.setTextColor(this.todayDateTextColor);
                }
            }
        }

        float size = textSize;
        if (size == -1)
            size = dayTextView.getTextSize();
        dayTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }
}
