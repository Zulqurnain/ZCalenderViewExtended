package jutt.com.extendedsample;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import jutt.com.zcalenderviewextended.month.DatePickerController;
import jutt.com.zcalenderviewextended.month.SimpleMonthAdapter;
import jutt.com.zcalenderviewextended.month.ZCalenderView;

public class MainActivity extends AppCompatActivity implements DatePickerController{

    ZCalenderView calendar_view;

    RelativeLayout progress_rl;

    ProgressBar pb_months;

    private HashMap<SimpleMonthAdapter.CalendarDay, Integer> calenderMonthEvents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb_months = findViewById(R.id.pb_months);
        calendar_view = findViewById(R.id.calendar_view);
        progress_rl = findViewById(R.id.progress_rl);

        calenderMonthEvents = new HashMap<>();

        pb_months.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(this,R.color.colorPrimaryDark), PorterDuff.Mode.MULTIPLY);


        // on click of specific date
        calendar_view.setController(this);

        // define if you want to show 1 month for this view only and vertical scroll is enable in this case
        calendar_view.setEnableHeightResize(false);

        calenderMonthEvents.put(
                new SimpleMonthAdapter.CalendarDay(getMilliSecondsFromDate("2017-08-20T10:00:00")),
                1
        );

        calenderMonthEvents.put(
                new SimpleMonthAdapter.CalendarDay(getMilliSecondsFromDate("2017-08-25T10:00:00")),
                1
        );

        calenderMonthEvents.put(
                new SimpleMonthAdapter.CalendarDay(getMilliSecondsFromDate("2017-07-20T10:00:00")),
                1
        );

        calenderMonthEvents.put(
                new SimpleMonthAdapter.CalendarDay(getMilliSecondsFromDate("2017-08-01T10:00:00")),
                1
        );

        calendar_view.setEventsHashMap(calenderMonthEvents);
        pb_months.setVisibility(View.GONE);
    }

    public static long getMilliSecondsFromDate(String time) {
        // String inputString = time.replace("T", " ");
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";//"yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
//        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US);
        inputFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = inputFormat.parse(time);
            // date= ISODateTimeFormat.dateTimeParser().parseDateTime(timestamp)
            return date.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void onDayOfMonthSelected(int year, int month, int day) {

        DateTime dateTime = new DateTime(year,month,day,0,0);

        // if click on event
        if(calenderMonthEvents.containsKey(new SimpleMonthAdapter.CalendarDay(dateTime.getMillis()))){
            Intent in = new Intent(this,WeekActivity.class);
            in.putExtra("SELECTED",dateTime);
            startActivity(in);
        }
    }
}
