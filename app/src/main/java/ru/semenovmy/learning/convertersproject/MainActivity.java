package ru.semenovmy.learning.convertersproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ru.semenovmy.learning.convertersproject.R;
import ru.semenovmy.learning.convertersproject.adapters.DataAdapter;
import ru.semenovmy.learning.convertersproject.model.Measure;

public class MainActivity extends AppCompatActivity {

    private List<Measure> measures = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        DataAdapter adapter = new DataAdapter(this, measures);
        recyclerView.setAdapter(adapter);
    }

    private void setInitialData(){
        measures.add(new Measure("Длина"));
        measures.add(new Measure("Масса"));
    }
}
