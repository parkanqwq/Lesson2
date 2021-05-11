package com.kalabukhov.lesson2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {

    private static final String APP_THEME = "APP_THEME";
    private static final String NAME_THEME = "THEME";
    private RadioButton radioButton1,radioButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.Theme_myTheme));
        setContentView(R.layout.activity_settings);

        View.OnClickListener radioButtonClickListener = v -> {
            RadioButton rb = (RadioButton)v;
            switch (rb.getId()) {
                case R.id.radio0:
                    setTheme(R.style.Theme_myTheme);
                    setAppTheme(R.style.Theme_myTheme);
                    recreate();
                    break;
                case R.id.radio1:
                    setTheme(R.style.Theme_AppCompat);
                    setAppTheme(R.style.Theme_AppCompat);
                    recreate();
                    break;
                default:
                    break;
            }
        };
        radioButton1 = findViewById(R.id.radio0);
        radioButton1.setOnClickListener(radioButtonClickListener);

        radioButton2 = findViewById(R.id.radio1);
        radioButton2.setOnClickListener(radioButtonClickListener);

        themeChange();
    }

    private void themeChange() {
        if (getAppTheme(R.style.Theme_myTheme) != R.style.Theme_myTheme){
            setTheme(R.style.Theme_AppCompat);
            setAppTheme(R.style.Theme_AppCompat);
            radioButton1.setChecked(false);
            radioButton2.setChecked(true);
        }
    }

    private int getAppTheme(int code){
        return getCodeStyle(code);
    }

    private void setAppTheme(int code){
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_THEME, MODE_PRIVATE);
        sharedPreferences.edit().putInt(APP_THEME, code).apply();
    }

    private int getCodeStyle(int codeStyle) {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_THEME, MODE_PRIVATE);
        return sharedPreferences.getInt(APP_THEME, codeStyle);
    }
}