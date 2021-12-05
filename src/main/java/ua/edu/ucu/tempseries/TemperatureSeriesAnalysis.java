package ua.edu.ucu.tempseries;

import java.util.Arrays;
import java.util.InputMismatchException;

import static java.lang.Math.*;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private Integer count;

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        checkInput(temperatureSeries);
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        this.count = temperatureSeries.length;
    }
    public void checkInput(double[] temperatureSeries){
        for (double value: temperatureSeries){
            if (value < -273){
                throw new InputMismatchException();
            }
        }
    }
    public boolean isEmpty(){
        return this.temperatureSeries.length == 0;
    }

    public double average() {
        if (isEmpty()){
            throw new IllegalArgumentException();
        }
        return sum()/this.temperatureSeries.length;
    }
    public double sum(){
        double tempValue;
        double sum_ = this.temperatureSeries[0];
        for (int index = 1;index<this.temperatureSeries.length;index++){
            tempValue = this.temperatureSeries[index];
            sum_ += tempValue;

        }
        return sum_;
    }

    public double deviation() {
        if (isEmpty()){
            throw new IllegalArgumentException();
        }
        double tempValue;
        double length = this.temperatureSeries.length;
        double averageValue = average();
        double sumSquares = 0;
        for (int index = 1;index<length;index++){
            tempValue = this.temperatureSeries[index];
            sumSquares += (tempValue - averageValue)*
                    (tempValue - averageValue);
        }
        sumSquares /= length;
        return sqrt(sumSquares);
    }

    public double min() {
        if (isEmpty()){
            throw new IllegalArgumentException();
        }
        return findTempClosestToValue(-1000);
    }

    public double max() {
        if (isEmpty()){
            throw new IllegalArgumentException();
        }
        return findTempClosestToValue(Double.POSITIVE_INFINITY);
    }

    public double findTempClosestToZero()
    {
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) {
        double tempEl;
        double closest = this.temperatureSeries[0];
        for (int index = 1;index<this.temperatureSeries.length;index++){
            tempEl = this.temperatureSeries[1];
            if (Math.abs(tempEl - tempValue) < Math.abs(closest - tempValue)){
                closest = tempEl;
            }

        }
        return closest;
    }

    public double[] findTempsLessThen(double tempValue) {
        int count = 0;
        double[] valuesLess = new double[1];
        for(double temp: this.temperatureSeries){
            if (temp < tempValue){
                if(valuesLess.length == count){
                    valuesLess = Arrays.copyOf(valuesLess, valuesLess.length+1);
                }
                valuesLess[count] = temp;
                count++;
            }
        }
        return valuesLess;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int count = 0;
        double[] valuesGreater = new double[1];
        for(double temp: this.temperatureSeries){
            if (temp > tempValue){
                if(valuesGreater.length == count){
                    valuesGreater = Arrays.copyOf(valuesGreater, valuesGreater.length+1);
                }
                valuesGreater[count] = temp;
                count++;
            }
        }
        return valuesGreater;
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(this.temperatureSeries);
    }

    public int addTemps(double... temps) {
        if (this.temperatureSeries.length == count){
            this.temperatureSeries = Arrays.copyOf(this.temperatureSeries, this.temperatureSeries.length*2);
        }
        for (double temp: temps){
            this.temperatureSeries[this.count] = temp;
            this.count++;
        }
        return count;
    }
}
