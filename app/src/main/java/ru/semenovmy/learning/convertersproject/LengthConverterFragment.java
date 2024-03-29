package ru.semenovmy.learning.convertersproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import ru.semenovmy.learning.convertersproject.adapters.DataAdapter;
import ru.semenovmy.learning.convertersproject.adapters.SpinnerAdapter;
import ru.semenovmy.learning.convertersproject.model.Measure;
import ru.semenovmy.learning.convertersproject.providers.LengthProvider;

public class LengthConverterFragment extends Fragment {

    public static final int POSITION_NaN = 0;
    private EditText mFromEditText;
    private EditText mResultEditText;
    private TextView mFromTextView;
    private TextView mResultTextView;
    private LengthProvider mLengthProvider = new LengthProvider();
    private DataAdapter dataAdapter;
    private Spinner spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_converter, container, false);
        dataAdapter = new DataAdapter(getResources());

        mFromEditText = view.findViewById(R.id.converter_from_edit_text);
        mFromEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String outputValue = lengthConvertor(mFromEditText.getText().toString(), mFromTextView.getText().toString(), mResultTextView.getText().toString());
                mResultEditText.setText(outputValue);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mResultEditText = view.findViewById(R.id.converter_result_edit_text);
        mFromTextView = view.findViewById(R.id.converter_from_text);
        mResultTextView = view.findViewById(R.id.converter_result_text);

        initFromSpinner(view);
        initToSpinner(view);
        return view;
    }

    public void initFromSpinner(View view) {
        spinner = view.findViewById(R.id.from_spinner);
        final List<String> spinnerItems = mLengthProvider.providerMeasures();
        Collections.sort(spinnerItems);
        spinnerItems.add(POSITION_NaN, getResources().getString(R.string.nothing));
        spinner.setAdapter(new SpinnerAdapter(spinnerItems));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final List<Measure> measures = position == POSITION_NaN ?
                        mLengthProvider.provideMeasures() :
                        mLengthProvider.filterBy(spinnerItems.get(position));
                dataAdapter.setMeasures(measures);
                mFromTextView.setText(spinnerItems.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initToSpinner(View view) {
        spinner = view.findViewById(R.id.result_spinner);
        final List<String> spinnerItems = mLengthProvider.providerMeasures();
        Collections.sort(spinnerItems);
        spinnerItems.add(POSITION_NaN, getResources().getString(R.string.nothing));
        spinner.setAdapter(new SpinnerAdapter(spinnerItems));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                final List<Measure> measures = position == POSITION_NaN ?
                        mLengthProvider.provideMeasures() :
                        mLengthProvider.filterBy(spinnerItems.get(position));
                dataAdapter.setMeasures(measures);
                mResultTextView.setText(spinnerItems.get(position));
                mResultEditText.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private String lengthConvertor(@NonNull String incomeValue, @NonNull String incomeMeasurement, @NonNull String resultMeasurment) {
        double incomeValueInKilometres = converterInKilometres(Integer.parseInt(incomeValue), incomeMeasurement);
        String outputValue = "";
        double out;

        if (resultMeasurment.equals(getString(R.string.millimeter))) {
            out = incomeValueInKilometres * 1000000.0;
            outputValue = Double.toString(out);}
        else if (resultMeasurment.equals(getString(R.string.centimeter))) {
            out = incomeValueInKilometres * 100000.0;
            outputValue = Double.toString(out);}
        else if (resultMeasurment.equals(getString(R.string.meter))) {
            out = incomeValueInKilometres * 1000.0;
            outputValue = Double.toString(out);}
        else if (resultMeasurment.equals(getString(R.string.kilometer))) {
            out = incomeValueInKilometres * 1.0;
            outputValue = Double.toString(out);}

        return outputValue;
    }

    private double converterInKilometres(@NonNull int incomeValue, @NonNull String incomeMeasurment) {

        double output = 0.0;

        if (incomeMeasurment.equals(getString(R.string.millimeter))) {
            output = incomeValue / 1000000.0;}
        else if (incomeMeasurment.equals(getString(R.string.centimeter))) {
            output = incomeValue / 100000.0;}
        else if (incomeMeasurment.equals(getString(R.string.meter))) {
            output = incomeValue / 1000.0;}
        else if (incomeMeasurment.equals(getString(R.string.kilometer))) {
            output = incomeValue / 1.0;}

        return output;
    }
}
