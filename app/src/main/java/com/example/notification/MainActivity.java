package com.example.notification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String CHANNEL_ID ="A001" ;
    Button signup;
    EditText entername;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            //Register the channel with the system;you can't change the importance
            //or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }

            //Hooks
            signup = (Button)findViewById(R.id.button);
            entername = (EditText) findViewById(R.id.nametext);



            signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String gname = entername.getText().toString();


                    final String message = "Hello! " + gname +  " Welcome to the MAD team";
                    //Create an explict intent for an Activity in your app

                    Intent intent = new Intent(MainActivity.this,Activity2.class);

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher_background).setContentTitle("My notification").setContentText(message).setPriority(NotificationCompat.PRIORITY_DEFAULT).setContentIntent(pendingIntent).setAutoCancel(true);

                    NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);

                    // notificationId is a uniqu int for each notification that you must define

                    notificationManager.notify(0,builder.build());

                }
            });




        }
    }

