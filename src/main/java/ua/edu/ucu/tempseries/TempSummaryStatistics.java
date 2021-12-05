package ua.edu.ucu.tempseries;

public final class TempSummaryStatistics {
    private final double avgTemp;
    private final double devTemp;
    private final double minTemp;
    private final double maxTemp;
    TempSummaryStatistics(double[] temperatureSeries){
        if (temperatureSeries.length == 0){
            throw new IllegalArgumentException();
        }
        TemperatureSeriesAnalysis temperatureSeriesAnalysis =
                new TemperatureSeriesAnalysis(temperatureSeries);
        this.avgTemp = temperatureSeriesAnalysis.average();
        this.devTemp = temperatureSeriesAnalysis.deviation();
        this.minTemp = temperatureSeriesAnalysis.min();
        this.maxTemp = temperatureSeriesAnalysis.max();
    }
}
