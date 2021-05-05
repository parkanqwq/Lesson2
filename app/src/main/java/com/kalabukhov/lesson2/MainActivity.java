package com.kalabukhov.lesson2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Button
                btnC, btnShare, btnMultiply, btnDelente,
                btn7, btn8, btn9, btnMin,
                btn6, btn5, btn4, btnPlas,
                btn3, btn2, btn1, btnEqually,
                btnPerecent, btn0, btnPoint;

    private ImageView themeBlack, themeWhile, imageView;

    private TextView textResult, textAct;
    private Map<View, String> map;
    private String waitNamberOne;
    private String waitNamberTwo;
    private double resultNumber;
    private boolean mathMethod;
    private int mathMethodId;

    private int perecent;
    private int multiply;
    private int share;
    private int plas;
    private int min;

    private SaveFileMain saveFileMain;

    private static final String APP_THEME = "APP_THEME";
    private static final String NAME_THEME = "THEME";
    private static final int WHILE_THEME = 0;
    private static final int BLACK_THEME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.Theme_myTheme));
        setContentView(R.layout.activity_main);

        initialization();
        createMap();
        btnMethod();
        themeChange();
    }

    private void themeChange() {
        if (getAppTheme(R.style.Theme_myTheme) != R.style.Theme_myTheme){
            themeBlack.setVisibility(View.GONE);
            themeWhile.setVisibility(View.VISIBLE);
            setTheme(R.style.Theme_AppCompat);
            setAppTheme(R.style.Theme_AppCompat);
            imageView.setVisibility(View.GONE);
        } else imageView.setVisibility(View.VISIBLE);

        themeBlack.setOnClickListener(view -> {
            themeBlack.setVisibility(View.GONE);
            themeWhile.setVisibility(View.VISIBLE);
            setTheme(R.style.Theme_AppCompat);
            setAppTheme(R.style.Theme_AppCompat);
            recreate();
        });

        themeWhile.setOnClickListener(view -> {
            themeWhile.setVisibility(View.GONE);
            themeBlack.setVisibility(View.VISIBLE);
            setTheme(R.style.Theme_myTheme);
            setAppTheme(R.style.Theme_myTheme);
            recreate();
        });
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

    public void createMap() {
        map = new HashMap<>();

        map.put(btnMultiply, " * ");
        map.put(btnShare, " / ");
        map.put(btnPlas, " + ");
        map.put(btnMin, " - ");
        map.put(btnPoint, ",");
        map.put(btnPerecent, " % ");

        map.put(btnC, "C");

        map.put(btn0, "0");
        map.put(btn1, "1");
        map.put(btn2, "2");
        map.put(btn3, "3");
        map.put(btn4, "4");
        map.put(btn5, "5");
        map.put(btn6, "6");
        map.put(btn7, "7");
        map.put(btn8, "8");
        map.put(btn9, "9");
    }
    private void btnMethod() {
        btnDelente.setOnClickListener(view -> {
            // доработать ошибку, если удаляется строка где значение уже ноль
            if (mathMethod) waitNamberTwo = waitNamberTwo.substring(0, waitNamberTwo.length() - 1);
            else waitNamberOne = waitNamberOne.substring(0, waitNamberOne.length() - 1);
            String textForTextAct = textAct.getText().toString();
            textForTextAct = textForTextAct.substring(0, textForTextAct.length() - 1);
            textAct.setText(textForTextAct);

            mathMethodCalculator(waitNamberOne, waitNamberTwo);
        });

        btnEqually.setOnClickListener(view -> {
            textAct.setText(textResult.getText());
            waitNamberOne = textResult.getText().toString();
            textResult.setVisibility(View.GONE);
        });

        btnPoint.setOnClickListener(view -> {
            if (mathMethod) waitNamberTwo += ".";
            else waitNamberOne +=  ".";
            textAct.setText(textAct.getText() + ".");
        });
    }

    public void metNumbers(View view){
        textResult.setVisibility(View.VISIBLE);
        for (Map.Entry<View, String> maps: map.entrySet()) {
            if (view.getId() == maps.getKey().getId()){

                if (view.getId() == btnPlas.getId() || view.getId() == btnMin.getId() || view.getId() == btnPerecent.getId()
                        || view.getId() == btnMultiply.getId() || view.getId() == btnShare.getId() && !textAct.getText().equals("")){
                    if (mathMethod){
                        waitNamberOne = textResult.getText().toString();
                        waitNamberTwo = "";
                    }
                    mathMethodId = view.getId();
                    mathMethod = true;
                }

                if (mathMethod == false && view.getId() != btnPlas.getId() && view.getId() != btnMin.getId()
                        && view.getId() != btnMultiply.getId() && view.getId() != btnShare.getId() && view.getId() != btnPerecent.getId()){
                    waitNamberOne = waitNamberOne + maps.getValue();
                }

                if (mathMethod && view.getId() != btnPlas.getId() && view.getId() != btnMin.getId()
                        && view.getId() != btnMultiply.getId() && view.getId() != btnShare.getId() && view.getId() != btnPerecent.getId()){
                    waitNamberTwo = waitNamberTwo + maps.getValue();

                    mathMethodCalculator(waitNamberOne, waitNamberTwo);

                    if (share == mathMethodId && waitNamberTwo.equals("0")) Toast.makeText(this, "Нельзя делить на ноль", Toast.LENGTH_SHORT).show();
                }

                if (!waitNamberTwo.equals("0")) {
                    textAct.setText(textAct.getText() + maps.getValue());
                }
            }
        }
    }

    private void mathMethodCalculator(String waitNamberOne, String waitNamberTwo) {
        resultNumber = Double.parseDouble(waitNamberOne);

        if (plas == mathMethodId) resultNumber = resultNumber + Double.parseDouble(waitNamberTwo);
        if (min == mathMethodId) resultNumber = resultNumber - Double.parseDouble(waitNamberTwo);
        if (multiply == mathMethodId) resultNumber = resultNumber * Double.parseDouble(waitNamberTwo);
        if (share == mathMethodId && Double.parseDouble(waitNamberTwo) != 0)
            resultNumber = resultNumber / Double.parseDouble(waitNamberTwo);
        if (perecent == mathMethodId) resultNumber = (resultNumber / 100) * Double.parseDouble(waitNamberTwo);

        textResult.setText(resultNumber + "");
    }

    public void btnC(View view){
        waitNamberOne = "";
        waitNamberTwo = "";
        textAct.setText("");
        textResult.setText("");
        mathMethod = false;
    }

    private void initialization() {
        btnMultiply = findViewById(R.id.btnMultiply);
        btnPerecent = findViewById(R.id.btnPerecent);
        btnEqually = findViewById(R.id.btnEqually);
        btnDelente = findViewById(R.id.btnDelente);
        textResult = findViewById(R.id.textResult);
        btnPoint = findViewById(R.id.btnPoint);
        btnShare = findViewById(R.id.btnShare);
        textAct = findViewById(R.id.textAct);
        btnPlas = findViewById(R.id.btnPlas);
        btnMin = findViewById(R.id.btnMin);
        btnC = findViewById(R.id.btnC);
        btn9 = findViewById(R.id.btn9);
        btn8 = findViewById(R.id.btn8);
        btn7 = findViewById(R.id.btn7);
        btn6 = findViewById(R.id.btn6);
        btn5 = findViewById(R.id.btn5);
        btn4 = findViewById(R.id.btn4);
        btn3 = findViewById(R.id.btn3);
        btn2 = findViewById(R.id.btn2);
        btn1 = findViewById(R.id.btn1);
        btn0 = findViewById(R.id.btn0);

        themeBlack = findViewById(R.id.themeBlack);
        themeWhile = findViewById(R.id.themeWhile);
        imageView = findViewById(R.id.imageView);

        waitNamberOne = "";
        waitNamberTwo = "";
        textAct.setText("");

        saveFileMain = new SaveFileMain();

        perecent = btnPerecent.getId();
        multiply = btnMultiply.getId();
        share = btnShare.getId();
        plas = btnPlas.getId();
        min = btnMin.getId();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        saveFileMain.setWaitNamberOne(waitNamberOne);
        saveFileMain.setWaitNamberTwo(waitNamberTwo);

        saveFileMain.setTextAct(textAct.getText().toString());
        saveFileMain.setTextResult(textResult.getText().toString());

        saveFileMain.setMathMethod(mathMethod);
        saveFileMain.setResultNumber(resultNumber);

        saveFileMain.setMathMethodId(mathMethodId);

        outState.putSerializable("main", saveFileMain);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        saveFileMain = (SaveFileMain) savedInstanceState.getSerializable("main");

        waitNamberOne = saveFileMain.getWaitNamberOne();
        waitNamberTwo = saveFileMain.getWaitNamberTwo();

        textAct.setText(saveFileMain.getTextAct());
        textResult.setText(saveFileMain.getTextResult());

        mathMethod = saveFileMain.isMathMethod();
        resultNumber = saveFileMain.getResultNumber();

        mathMethodId = saveFileMain.getMathMethodId();
    }
}