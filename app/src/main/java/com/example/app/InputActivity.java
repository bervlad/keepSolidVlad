package com.example.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.app.collections.Car;
import com.example.app.utils.listeners.Constants;

public class InputActivity extends AppCompatActivity {
    private AppCompatEditText carName;
    private AppCompatEditText carColor;
    private AppCompatEditText maxSpeed;
    private AppCompatEditText capacity;
    private AppCompatButton sendBtn;
    private AppCompatButton cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        carName = findViewById(R.id.car_name);
        carColor = findViewById(R.id.car_color);
        maxSpeed = findViewById(R.id.car_m_speed);
        capacity = findViewById(R.id.car_capacity);

        sendBtn = findViewById(R.id.btn_send);
        cancelBtn = findViewById(R.id.btn_cancel);

        setListeners();

    }

    private void setListeners() {

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendBtn();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleCancelBtn();
            }
        });
    }

    private void handleCancelBtn() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private void handleSendBtn() {


            Intent intent = new Intent();
            Boolean check = true;

            String maxSpeedString = maxSpeed.getText().toString().trim().replaceAll(" +", " ");
            String capacityString = capacity.getText().toString().trim().replaceAll(" +", " ");
            String carNameString = carName.getText().toString().trim().replaceAll(" +", " ");
            String carColorString = carColor.getText().toString().trim().replaceAll(" +", " ");

            if (maxSpeedString.length() == 0) {
                maxSpeed.setError(getString(R.string.no_value_provided));
                check = false;
            }
            if (capacityString.length() == 0) {
                capacity.setError(getString(R.string.no_value_provided));
                check = false;
            }
            if (carNameString.length() == 0) {
                carName.setError(getString(R.string.no_value_provided));
                check = false;
            }
            if (carColorString.length() == 0) {
                carColor.setError(getString(R.string.no_value_provided));
                check = false;
            }

            try {
                Integer.parseInt(maxSpeedString);
            } catch (Exception e) {
                maxSpeed.setError(getString(R.string.input_number));
                check = false;
            }

            try {
                Integer.parseInt(capacityString);
            } catch (Exception e) {
                capacity.setError(getString(R.string.input_number));
                check = false;
            }

            if (check) {
                Car newCar = new Car(carNameString,
                        carColorString,
                        Integer.parseInt(maxSpeed.getText().toString()),
                        Integer.parseInt(capacity.getText().toString()));
                intent.putExtra(Constants.EXTRA_CAR, newCar);
                setResult(RESULT_OK, intent);
                finish();
            }

        }
    }
