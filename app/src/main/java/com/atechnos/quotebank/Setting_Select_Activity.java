package com.atechnos.quotebank;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atechnos.quotebank.Adapter.InterestGridViewAdpater;

public class Setting_Select_Activity extends AppCompatActivity {
    Dialog myDialog;
    RelativeLayout rlBackground;
    ImageView ivQBLogo, ivNext;
    TextView tvSelect;
    static Setting_Select_Activity setting_select_activity;

    GridView androidGridView;

    String[] gridViewString = {
            "General", "Sports", "Music", "Tech", "Politics", "Movies",
            "Reading/Writing", "Art", "Business", "Fitness/Health"
    };
    String check;

    int[] gridViewImageId = {
            R.drawable.option_generalmotivation, R.drawable.sports_option, R.drawable.music_option,
            R.drawable.tech_option, R.drawable.politics_option, R.drawable.movies_option,
            R.drawable.readingwriting_option, R.drawable.art_option,
            R.drawable.business_option, R.drawable.fitness_option
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_setting_select);
        Intent intent = getIntent();
        check=intent.getStringExtra("act");
        setFindView();
        setNotification();


    }

    private void setFindView() {
        rlBackground = (RelativeLayout) findViewById(R.id.rlBackground);
        ivQBLogo = (ImageView) findViewById(R.id.ivQBLogo);
        tvSelect = (TextView) findViewById(R.id.tvSelect);
    }

    private void setNotification() {
        if (NotificationManagerCompat.from(this).areNotificationsEnabled()) {
            tvSelect.setVisibility(View.VISIBLE);
            // ivNext.setVisibility(View.VISIBLE);
            ivQBLogo.setVisibility(View.VISIBLE);
            setGridData();
            Toast.makeText(this, "App Notification is ON", Toast.LENGTH_SHORT).show();
        } else {
            Log.e("Heleo", "=====false===");
            showDialog();
        }
    }

    private void showDialog() {
        myDialog = new Dialog(Setting_Select_Activity.this);
        myDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        myDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        // myDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        myDialog.setContentView(R.layout.dialogbox_setting);
        // myDialog.setTitle("Instructions of $infor")
        myDialog.setCancelable(false);


        TextView tvDontAllow = (TextView) myDialog.findViewById(R.id.tvDontAllow);
        TextView tvOk = (TextView) myDialog.findViewById(R.id.tvOk);
        TextView tvheading = (TextView) myDialog.findViewById(R.id.tvheading);
        tvheading.setText("" + R.string.pop_heading);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            tvheading.setText(Html.fromHtml("<i>\"App\" </i>" + getString(R.string.pop_heading), Html.FROM_HTML_MODE_COMPACT));
        } else {
            tvheading.setText(Html.fromHtml("<i>/\"App\" </i>" + getString(R.string.pop_heading)));
        }
        tvDontAllow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                Setting_Select_Activity.this.finish();
            }
        });

        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNotification();
                myDialog.dismiss();
                //   Setting_Select_Activity.this.finish();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    rlBackground.setBackground(getDrawable(R.drawable.bg3));

                }
                //   rlBackground.setBackgroundResource(getResources().getDrawable(R.drawable.screen4bg));
                Intent intent = new Intent();
                intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");

//for Android 5-7
                intent.putExtra("app_package", getPackageName());
                intent.putExtra("app_uid", getApplicationInfo().uid);

// for Android 8 and above
                intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());

                startActivityForResult(intent, 2);

            }
        });
        myDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 2) {
            tvSelect.setVisibility(View.VISIBLE);
            // ivNext.setVisibility(View.VISIBLE);
            ivQBLogo.setVisibility(View.VISIBLE);
            setGridData();
        }
    }

    private void setGridData() {
        androidGridView = (GridView) findViewById(R.id.gvInterest);
        androidGridView.setVisibility(View.VISIBLE);
        InterestGridViewAdpater adapterViewAndroid = new InterestGridViewAdpater(Setting_Select_Activity.this, gridViewString, gridViewImageId);

        androidGridView.setAdapter(adapterViewAndroid);
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
              //  Toast.makeText(Setting_Select_Activity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
                Intent in = new Intent(Setting_Select_Activity.this, ThoughtActivity.class);
                in.putExtra("select", gridViewString[+i]);
                startActivity(in);
            }
        });


    }

    private void addNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("Notifications Example")
                        .setContentText("This is a test notification");

        Intent notificationIntent = new Intent(this, FinalActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
