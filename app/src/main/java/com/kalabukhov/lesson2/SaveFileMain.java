package com.kalabukhov.lesson2;

import java.io.Serializable;

public class SaveFileMain implements Serializable {

    private String waitNamberOne;
    private String waitNamberTwo;
    private String textAct;
    private String textResult;
    private double resultNumber;
    private boolean mathMethod;
    private int mathMethodId;

    public int getMathMethodId() {
        return mathMethodId;
    }

    public void setMathMethodId(int mathMethodId) {
        this.mathMethodId = mathMethodId;
    }

    public String getWaitNamberOne() {
        return waitNamberOne;
    }

    public void setWaitNamberOne(String waitNamberOne) {
        this.waitNamberOne = waitNamberOne;
    }

    public String getWaitNamberTwo() {
        return waitNamberTwo;
    }

    public void setWaitNamberTwo(String waitNamberTwo) {
        this.waitNamberTwo = waitNamberTwo;
    }

    public String getTextAct() {
        return textAct;
    }

    public void setTextAct(String textAct) {
        this.textAct = textAct;
    }

    public String getTextResult() {
        return textResult;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }

    public double getResultNumber() {
        return resultNumber;
    }

    public void setResultNumber(double resultNumber) {
        this.resultNumber = resultNumber;
    }

    public boolean isMathMethod() {
        return mathMethod;
    }

    public void setMathMethod(boolean mathMethod) {
        this.mathMethod = mathMethod;
    }
}
