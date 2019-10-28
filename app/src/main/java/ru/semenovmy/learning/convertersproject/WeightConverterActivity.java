package ru.semenovmy.learning.convertersproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class WeightConverterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new WeightConverterFragment ()).commit();
        }
    }
}
