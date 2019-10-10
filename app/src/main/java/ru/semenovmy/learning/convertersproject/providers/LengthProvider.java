package ru.semenovmy.learning.convertersproject.providers;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ru.semenovmy.learning.convertersproject.model.Measure;

public class LengthProvider {

    private List<Measure> mValues = Arrays.asList(
            new Measure("Миллиметр"),
            new Measure("Сантиметр"),
            new Measure("Метр"),
            new Measure("Километр")
    );

    public List<Measure> provideMeasures() {
        return new ArrayList<>(mValues);
    }

    public List<String> providerMeasures() {
        Set<String> measuresSet = new HashSet<>();
        for (Measure measure : mValues) {
            measuresSet.add(measure.getName());
        }
        return new ArrayList<>(measuresSet);
    }

    public List<Measure> filterBy(@NonNull String lectorName) {
        List<Measure> result = new ArrayList<>();
        for (Measure measure : mValues) {
            if (measure.getName().equals(lectorName)) {
                result.add(measure);
            }
        }
        return result;
    }
}
