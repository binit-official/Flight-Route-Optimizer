package model;

public class FlightRoute {
    private Airport source;
    private Airport destination;
    private double distance;
    private double baseCost;
    private double weatherImpact;

    public FlightRoute(Airport source, Airport destination, double distance) {
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.baseCost = distance * 0.12; // Base cost calculation
        this.weatherImpact = 0;
    }

    public void setWeatherImpact(double impact) {
        this.weatherImpact = impact;
        this.baseCost = distance * (0.12 + impact);
    }

    public double getCurrentCost() { return baseCost; }
    public Airport getSource() { return source; }
    public Airport getDestination() { return destination; }
}