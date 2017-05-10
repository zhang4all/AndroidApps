package com.example.android.project1;

/**
 * Created by zhang4all on 2/4/2017.
 */

public class RegexPattern {
    public final int patternNumber;
    private boolean patternFound=false;
    private int patternStartIdx;
    private String firstValidNumber;

    public String getFirstValidNumber() {
        return firstValidNumber;
    }

    public void setFirstValidNumber(String firstValidNumber) {
        this.firstValidNumber = firstValidNumber;
    }

    public RegexPattern(int num) {
        patternNumber = num;
    }

    public int getPatternStartIdx() {
        return patternStartIdx;
    }

    public void setPatternStartIdx(int patternStartIdx) {
        this.patternStartIdx = patternStartIdx;
    }

    public boolean isPatternFound() {

        return patternFound;
    }

    public void setPatternFound(boolean patternFound) {
        this.patternFound = patternFound;
    }
}
