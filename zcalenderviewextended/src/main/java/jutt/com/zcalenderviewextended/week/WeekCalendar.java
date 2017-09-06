package jutt.com.zcalenderviewextended.week;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.CircularArray;
import android.support.v4.util.CircularIntArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import jutt.com.zcalenderviewextended.R;
import jutt.com.zcalenderviewextended.week.decorator.DayDecorator;
import jutt.com.zcalenderviewextended.week.listener.OnDateClickListener;
import jutt.com.zcalenderviewextended.week.listener.OnWeekChangeListener;
import jutt.com.zcalenderviewextended.week.view.WeekPager;
import jutt.com.zcalenderviewextended.week.decorator.DayDecorator;
import jutt.com.zcalenderviewextended.week.decorator.DefaultDayDecorator;
import jutt.com.zcalenderviewextended.week.eventbus.BusProvider;
import jutt.com.zcalenderviewextended.week.eventbus.Event;
import jutt.com.zcalenderviewextended.week.listener.OnDateClickListener;
import jutt.com.zcalenderviewextended.week.listener.OnWeekChangeListener;
import jutt.com.zcalenderviewextended.week.view.WeekPager;

/**
 * Created by nor on 12/6/2015.
 */
public class WeekCalendar extends LinearLayout {
    private static final String TAG = "WeekCalendarTAG";
    private OnDateClickListener listener;
    private TypedArray typedArray;
    private GridView daysName;
    private DayDecorator dayDecorator;
    private OnWeekChangeListener onWeekChangeListener;

    private BaseAdapter weekNamesAdapter;

    // pararms
    public static int[] weekDaysNames; // DateTimeConstants based array
    private WeekPager weekPager;


    public WeekCalendar(Context context) {
        super(context);
        init(null);
    }

    public WeekCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

    }

    public WeekCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);

    }

    private void init(AttributeSet attrs) {
        int firstDayOfWeek = 1;
        if (attrs != null) {
            typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.WeekCalendar);
            int selectedDateColor = typedArray.getColor(R.styleable
                    .WeekCalendar_selectedBgColor, ContextCompat.getColor(getContext(), R.color
                    .colorAccent));
            int todayDateColor = typedArray.getColor(R.styleable
                    .WeekCalendar_todaysDateBgColor, ContextCompat.getColor(getContext(), R.color
                    .colorAccent));
            int daysTextColor = typedArray.getColor(R.styleable
                    .WeekCalendar_daysTextColor, Color.WHITE);
            int weekTextColor = typedArray.getColor(R.styleable
                    .WeekCalendar_weekDaysTextColor, Color.WHITE);
            float daysTextSize = typedArray.getDimension(R.styleable
                    .WeekCalendar_daysTextSize, -1);
            int todayDateTextColor = typedArray.getColor(R.styleable
                    .WeekCalendar_todaysDateTextColor, ContextCompat.getColor(getContext(), android.R.color.white));
            firstDayOfWeek = typedArray.getColor(R.styleable
                    .WeekCalendar_firstDayOfWeek, 1);

            setDayDecorator(new DefaultDayDecorator(getContext(),
                    selectedDateColor,
                    todayDateColor,
                    todayDateTextColor,
                    daysTextColor,
                    weekTextColor,
                    daysTextSize));
        }
        setOrientation(VERTICAL);

        if (!typedArray.getBoolean(R.styleable.WeekCalendar_hideNames, false)) {
            daysName = getDaysNames(firstDayOfWeek);
            addView(daysName, 0);
        }

        weekPager = new WeekPager(getContext(), attrs);
        addView(weekPager);

    }

    /***
     * Do not use this method
     * this is for receiving date,
     * use "setOndateClick" instead.
     */
    @Subscribe
    public void onDateClick(Event.OnDateClickEvent event) {
        if (listener != null)
            listener.onDateClick(event.getDateTime());
    }

    @Subscribe
    public void onDayDecorate(Event.OnDayDecorateEvent event) {
        if (dayDecorator != null) {
            dayDecorator.decorate(event.getView(), event.getDayTextView(), event.getDateTime(),
                    event.getFirstDay(), event.getSelectedDateTime());
        }
    }

    @Subscribe
    public void onWeekChange(Event.OnWeekChange event) {
        if (onWeekChangeListener != null) {
            onWeekChangeListener.onWeekChange(event.getFirstDayOfTheWeek(), event.isForward());
        }
    }


    public void setOnDateClickListener(OnDateClickListener listener) {
        this.listener = listener;
    }

    public void setDayDecorator(DayDecorator decorator) {
        this.dayDecorator = decorator;
    }

    public void setOnWeekChangeListener(OnWeekChangeListener onWeekChangeListener) {
        this.onWeekChangeListener = onWeekChangeListener;
    }

    private GridView getDaysNames(final int firstDayOfWeek) {
        daysName = new GridView(getContext());
        daysName.setSelector(new StateListDrawable());
        daysName.setNumColumns(7);

        weekNamesAdapter = new BaseAdapter() {
            private String[] days = getWeekDayNames();

            public int getCount() {
                return days.length;
            }

            @Override
            public String getItem(int position) {
                return days[position];
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @SuppressLint("InflateParams")
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    LayoutInflater inflater = LayoutInflater.from(getContext());
                    convertView = inflater.inflate(R.layout.week_day_grid_item, null);
                }
                TextView day = (TextView) convertView.findViewById(R.id.daytext);
                day.setText(days[position]);
                if (typedArray != null) {
                    if(day.getText().charAt(0) == 'S'){ // its a weekday
                        day.setTextColor(typedArray.getColor(R.styleable
                                .WeekCalendar_weekDaysTextColor, Color.WHITE));
                    }else{
                        day.setTextColor(typedArray.getColor(R.styleable.WeekCalendar_weekTextColor,
                                Color.WHITE));
                    }
                    day.setTextSize(TypedValue.COMPLEX_UNIT_PX, typedArray.getDimension(R.styleable
                            .WeekCalendar_weekTextSize, day.getTextSize()));
                }
                return convertView;
            }

            private String[] getWeekDayNames() {
                DateFormatSymbols df = DateFormatSymbols.getInstance();
                String[] Originalweekdays = df.getWeekdays();
                List<String> weekdays = new ArrayList<>();
                String firstDayOfWeek_ = Originalweekdays[firstDayOfWeek];

                CircularArray<String> daysName = new CircularArray<>();

                for(String s:Originalweekdays){
                    daysName.addLast(s);
                    weekdays.add(s);
                }

                weekdays.remove(0);
                daysName.popFirst(); // extra space

                Originalweekdays = weekdays.toArray(new String[weekdays.size()]);

                int i = 0;
                boolean foundEle = false;
                while( i < 7 ){
                    if(!foundEle){
                        String day = daysName.popFirst();
                        if(!day.equals(firstDayOfWeek_)){
                            daysName.addLast(day);
                        }else{
                            daysName.addFirst(day);
                            foundEle = true;
                        }
                    }
                    i++;
                }

                weekDaysNames = new int[7];

                for(int j = 0;j<daysName.size();j++){
                    Originalweekdays[j] = daysName.get(j);
                }

                weekDaysNames = new int[weekdays.size()];
                for(i = 0; i<weekdays.size(); i++){
                    weekDaysNames[i] = weekdays.indexOf(Originalweekdays[i])+1;
                }

                if (typedArray.getInt(R.styleable.WeekCalendar_dayNameLength, 0) == 0)
                    for (int j = 0; j < Originalweekdays.length; j++)
                        Originalweekdays[j] = Originalweekdays[j].substring(0, 1);

                return Originalweekdays;

            }
        };

        daysName.setAdapter(weekNamesAdapter);
        if (typedArray != null)
            daysName.setBackgroundColor(typedArray.getColor(R.styleable
                    .WeekCalendar_weekBackgroundColor, ContextCompat.getColor(getContext(), R
                    .color.colorPrimary)));
        return daysName;
    }

    /**
     * Renders the days again. If you depend on deferred data which need to update the calendar
     * after it's resolved to decorate the days.
     */
    public void updateUi() {
        BusProvider.getInstance().post(new Event.OnUpdateUi());
    }

    public void moveToPrevious() {
        BusProvider.getInstance().post(new Event.UpdateSelectedDateEvent(-1));
    }

    public void moveToNext() {
        BusProvider.getInstance().post(new Event.UpdateSelectedDateEvent(1));
    }

    public void reset() {
        BusProvider.getInstance().post(new Event.ResetEvent());
    }

    public void setSelectedDate(DateTime selectedDate) {
        BusProvider.getInstance().post(new Event.SetSelectedDateEvent(selectedDate));
    }

    public void setStartDate(DateTime startDate) {
        BusProvider.getInstance().post(new Event.SetStartDateEvent(startDate));
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BusProvider.getInstance().unregister(this);
        BusProvider.disposeInstance();
    }
}
