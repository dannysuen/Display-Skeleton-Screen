package com.lazada.displayskeletonscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View background = findViewById(R.id.activity_main);

        background.setBackgroundDrawable(new RepeatingCellDrawable(getResources().getDrawable(R.drawable.sample_item),
                getResources().getDimensionPixelSize(R.dimen.item_height)));
    }
}
