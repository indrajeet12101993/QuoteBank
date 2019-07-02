package com.atechnos.quotebank;

import android.annotation.TargetApi;
import android.app.TimePickerDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.atechnos.quotebank.timerNotification.AlarmReceiver;
import com.atechnos.quotebank.timerNotification.AlarmReceiverSleep;
import com.atechnos.quotebank.timerNotification.LocalData;
import com.atechnos.quotebank.timerNotification.LocalDataSleep;
import com.atechnos.quotebank.timerNotification.NotificationScheduler;
import com.atechnos.quotebank.timerNotification.NotificationSchedulerSleep;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class TimerActivity extends AppCompatActivity {
    String TAG = "RemindMe";
    LocalData localData;
    LocalDataSleep localDataSleep;
    SessionManager sessionManager;
    TextView tvLine2, tvLine5, tvLine6, tvLine10, tvLine11, tvWakeClock, tvSleepClock, tvWakeQuotes, tvSleepeQuotes;
    ImageView ivWakeClock, ivSleepClock, ivBack, imageView, ivWakeQuotes, ivSleepQuotes;
    String[] quotes;
    String[] author;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timer);
        sessionManager = new SessionManager(this);
        localData = new LocalData(getApplicationContext());
        quotes = getIntent().getStringArrayExtra("arrayQuotes");
        author = getIntent().getStringArrayExtra("arrayAuthor");

        AppController.getInstance().setQutoes(quotes);
        AppController.getInstance().setAuthor(author);
        String randomquotes = quotes[new Random().nextInt(quotes.length)];
        String randomauthor = author[new Random().nextInt(author.length)];
        localDataSleep = new LocalDataSleep(getApplicationContext());
        localData.setQuotes(randomquotes);
        localData.setAuthor(randomauthor);
        localDataSleep.setAuthor(randomauthor);
        localDataSleep.setQuotes(randomquotes);
        NotificationScheduler.setReminder(TimerActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());

        NotificationSchedulerSleep.setReminder(TimerActivity.this, AlarmReceiverSleep.class, localDataSleep.get_hour(), localDataSleep.get_min());
        setFindByViewIds();
        setVlaueTextView();
        setOnClickListeners();

    }

    private void setOnClickListeners() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd/MM/yyyy");
        final String strDate = mdformat.format(calendar.getTime());

        ivSleepClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if (localData.getReminderStatus())
                showTimePickerDialogSleep(localDataSleep.get_hour(), localDataSleep.get_min(), strDate);
            }
        });
        ivWakeClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if (localData.getReminderStatus())
                showTimePickerDialogWake(localData.get_hour(), localData.get_min(), strDate);
            }
        });
    }

    private void setVlaueTextView() {
        String text = "<font color=#ffffff>should</font> <font color=#FFEB3B>start</font> <font color=#ffffff>and</font> <font color=#FFEB3B>end</font> <font color=#ffffff>the day on a</font> ";
        String text5 = "<font color=#ffffff>typically</font> <font color=#FFEB3B>wake up</font> ";
        String text6 = "<font color=#ffffff>and</font> <font color=#FFEB3B>go to sleep.</font> ";
        String text10 = "<font color=#ffffff>Set</font> <font color=#FFEB3B>Wake - Up </font> <font color=#ffffff>Time :</font> ";
        String text11 = "<font color=#ffffff>Set</font> <font color=#FFEB3B>Sleep</font> <font color=#ffffff>Time :</font> ";


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvLine2.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
            tvLine5.setText(Html.fromHtml(text5, Html.FROM_HTML_MODE_COMPACT));
            tvLine6.setText(Html.fromHtml(text6, Html.FROM_HTML_MODE_COMPACT));
            tvLine10.setText(Html.fromHtml(text10, Html.FROM_HTML_MODE_COMPACT));
            tvLine11.setText(Html.fromHtml(text11, Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvLine2.setText(Html.fromHtml(text));
            tvLine5.setText(Html.fromHtml(text5));
            tvLine6.setText(Html.fromHtml(text6));
            tvLine10.setText(Html.fromHtml(text10));
            tvLine11.setText(Html.fromHtml(text11));
        }

    }

    private void setFindByViewIds() {
        tvLine2 = (TextView) findViewById(R.id.tvLine2);
        tvLine5 = (TextView) findViewById(R.id.tvLine5);
        tvLine6 = (TextView) findViewById(R.id.tvLine6);
        tvLine10 = (TextView) findViewById(R.id.tvLine10);
        tvLine11 = (TextView) findViewById(R.id.tvLine11);
        tvWakeClock = (TextView) findViewById(R.id.tvWakeClock);
        tvSleepClock = (TextView) findViewById(R.id.tvSleepClock);
        ivSleepClock = (ImageView) findViewById(R.id.ivSleepClock);
        ivWakeClock = (ImageView) findViewById(R.id.ivWakeClock);

        imageView = (ImageView) findViewById(R.id.imageView);
    }

    private void showTimePickerDialogSleep(int h, final int m, final String strDate) {

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.timepicker_header, null);
        TextView tvMode = (TextView) view.findViewById(R.id.tvMode);
        tvMode.setText("Sleep Remind Me @");
        TimePickerDialog builder = new TimePickerDialog(this, R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        Log.e(TAG, "onTimeSet: hour===== " + hour);
                        Log.e(TAG, "onTimeSet: min======== " + min);
                        String changeformat = getFormatedTimeOld(hour, min);
                        getTimeBefore10minutes(strDate + " " + changeformat);
                        String vla = getTimeBefore10minutes(strDate + " " + changeformat);
                        Log.e(TAG, "onTimeSet: replace======= " + vla);
                        vla = vla.replace(" ", ",");
                        Log.e(TAG, "onTimeSet: comma======= " + vla);
                        String[] parts = vla.split(",");
                        String time = parts[1];
                        Log.e(TAG, "Integer Part====sleep=====: " + time);

                        time = time.replace(":", ",");
                        String[] times1 = time.split(",");
                        String hours = times1[0];
                        String minutes = times1[1];
                        Log.e(TAG, "Integer Part====sleep=====: " + hours + "======================" + minutes);

                        localDataSleep.set_hour(Integer.parseInt(hours));
                        localDataSleep.set_min(Integer.parseInt(minutes));
                        tvSleepClock.setVisibility(View.VISIBLE);
                        tvSleepClock.setText(getFormatedTime(hour, min));
                        sessionManager.setTimeSleep(getFormatedTime(hour, min));
                        ivSleepClock.setVisibility(View.INVISIBLE);
                        NotificationSchedulerSleep.setReminder(TimerActivity.this, AlarmReceiverSleep.class, localDataSleep.get_hour(), localDataSleep.get_min());


                    }
                }, h, m, false);

        builder.setCustomTitle(view);
        builder.show();

    }

    public static String getTimeBefore10minutes(String currentDate) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            // Get calendar set to current date and time with Singapore time zone
            Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Asia/Calcutta"));
            calendar.setTime(format.parse(currentDate));

            //Set calendar before 10 minutes
            calendar.add(Calendar.MINUTE, -10);
            //Formatter
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            formatter.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
            return formatter.format(calendar.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    private void showTimePickerDialogWake(int h, final int m, final String strDate) {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.timepicker_header, null);
        TextView tvMode = (TextView) view.findViewById(R.id.tvMode);
        tvMode.setText("Wake-Up Remaind Me @");
        TimePickerDialog builder = new TimePickerDialog(this, R.style.DialogTheme,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        Log.e(TAG, "onTimeSet: hour===== " + hour);
                        Log.e(TAG, "onTimeSet: min======== " + min);
                        String changeformat = getFormatedTimeOld(hour, min);
                        getTimeAfter10minutes(strDate + " " + changeformat);
                        String vla = getTimeAfter10minutes(strDate + " " + changeformat);
                        Log.e(TAG, "onTimeSet: replace====wake=== " + vla);
                        vla = vla.replace(" ", ",");
                        Log.e(TAG, "onTimeSet: comma======= " + vla);
                        String[] parts = vla.split(",");
                        String time = parts[1];
                        Log.e(TAG, "Integer Part====wake=====: " + time);

                        time = time.replace(":", ",");
                        String[] times1 = time.split(",");
                        String hours = times1[0];
                        String minutes = times1[1];
                        Log.e(TAG, "Integer Part====wake=====: " + hours + "======================" + minutes);

                        localData.set_hour(Integer.parseInt(hours));
                        localData.set_min(Integer.parseInt(minutes));
                        tvWakeClock.setVisibility(View.VISIBLE);
                        tvWakeClock.setText(getFormatedTime(hour, min));
                        sessionManager.setTimeSleep(getFormatedTime(hour, min));
                        ivWakeClock.setVisibility(View.INVISIBLE);
                        NotificationScheduler.setReminder(TimerActivity.this, AlarmReceiver.class, localData.get_hour(), localData.get_min());
                    }
                }, h, m, false);

        builder.setCustomTitle(view);
        builder.show();
    }

    public static String getTimeAfter10minutes(String currentDate) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            // Get calendar set to current date and time with Singapore time zone
            Calendar calendar = new GregorianCalendar(TimeZone.getTimeZone("Asia/Calcutta"));
            calendar.setTime(format.parse(currentDate));

            //Set calendar after 10 minutes
            calendar.add(Calendar.MINUTE, 10);
            //Formatter
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            formatter.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
            return formatter.format(calendar.getTime());
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public String getFormatedTime(int h, int m) {
        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "hh:mm a";

        String oldDateString = h + ":" + m;
        String newDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, getCurrentLocale());
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            newDateString = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateString;
    }

    public String getFormatedTimeOld(int h, int m) {
        final String OLD_FORMAT = "HH:mm";
        final String NEW_FORMAT = "hh:mm a";

        String oldDateString = h + ":" + m;
        String newDateString = "";

        try {
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT, getCurrentLocale());
            Date d = sdf.parse(oldDateString);
            sdf.applyPattern(OLD_FORMAT);
            newDateString = sdf.format(d);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newDateString;
    }


    @TargetApi(Build.VERSION_CODES.N)
    public Locale getCurrentLocale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            return getResources().getConfiguration().locale;
        }
    }


}
