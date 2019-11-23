package com.example.xmlfag;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.animation.*;

public class MainActivity extends AppCompatActivity {

    ImageView imageViewFlag;
    Button btnWave;
    Integer[] imageId = {R.drawable.canflag};
    private Animation waveAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewFlag = findViewById(R.id.ImageView1);
        btnWave = findViewById(R.id.BtnWave);

        btnWave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewFlag.setVisibility(View.VISIBLE);
                imageViewFlag.setImageResource(imageId[0]);
                waveFlag();
//                imageViewFlag.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void waveFlag()
    {
        waveAnimation = AnimationUtils.loadAnimation(this, R.anim.wave);
        imageViewFlag.startAnimation(waveAnimation);
    }

}
