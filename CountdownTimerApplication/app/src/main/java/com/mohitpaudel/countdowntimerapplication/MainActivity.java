package com.mohitpaudel.countdowntimerapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private TextView eggTxtView;
    private CountDownTimer countDownTimer;
    private boolean countdownCheck;
    private Button changeButton;
    private MediaPlayer mediaPlayer;


    public void resetTimer()
    {
        countDownTimer.cancel();
        seekBar.setEnabled(true);
        changeButton.setText("Go");
        seekBar.setProgress(30);
        countdownCheck=false;
        eggTxtView.setText("0:30");

    }


    public void updateTimer(int i)
    {
        int minutes=(int) i/60;
        int seconds=i-minutes*60;
        String minText="";
        String secText="";
        minText=Integer.toString(minutes);
        secText=Integer.toString(seconds);
        if(seconds<=9)
        {
            secText="0"+secText;
        }

        eggTxtView=(TextView) findViewById(R.id.eggTxtView);
        eggTxtView.setText(minText+":"+secText);

    }

    public void clickFunction(View view)
    {

       if(countdownCheck==false) {
           countdownCheck = true;
           seekBar.setEnabled(false);
           changeButton.setText("Stop");
           countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000) {
               public void onTick(long millisUntilFinished) {
                   updateTimer((int) millisUntilFinished / 1000);
               }

               public void onFinish() {
                   resetTimer();
                   mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                   mediaPlayer.start();
               }
           }.start();
       }else
       {
            resetTimer();
       }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        seekBar=(SeekBar) findViewById(R.id.seeker);
        seekBar.setMax(600);
        seekBar.setProgress(30);



        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        changeButton=(Button) findViewById(R.id.clickButton);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
