package graph;

import model.Airport;
import model.FlightRoute;
import java.util.*;

public class FlightGraph {
    private Map<Airport, List<FlightRoute>> adjacencyList = new HashMap<>();

    public void addAirport(Airport airport) {
        adjacencyList.putIfAbsent(airport, new ArrayList<>());
    }

    public void addRoute(FlightRoute route) {
        // Ensure the source airport exists in the graph
        if (!adjacencyList.containsKey(route.getSource())) {
            addAirport(route.getSource()); // Add if missing
        }
        adjacencyList.get(route.getSource()).add(route);
    }

    public List<FlightRoute> getRoutesFromAirport(Airport airport) {
        return adjacencyList.getOrDefault(airport, Collections.emptyList());
    }

    public Set<Airport> getAllAirports() {
        return adjacencyList.keySet();
    }
}