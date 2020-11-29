package com.example.a2_retrofit.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.a2_retrofit.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction()
                .setReorderingAllowed(true)
                .add(R.id.fragment_view, RetrofitFragment.class, null)
                .commit();
    }
}