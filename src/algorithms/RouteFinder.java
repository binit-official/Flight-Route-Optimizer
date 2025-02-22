package algorithms;

import model.Airport;
import model.FlightRoute;
import graph.FlightGraph;
import java.util.*;

public class RouteFinder {
    private static final int MAX_ROUTES = 3;

    public static List<Route> findTopRoutes(FlightGraph graph, Airport source, Airport dest) {
        PriorityQueue<Route> pq = new PriorityQueue<>(Comparator.comparingDouble(r -> r.totalCost));
        Map<Airport, Integer> visitCounts = new HashMap<>();

        pq.add(new Route(new ArrayList<>(List.of(source)), 0.0));

        List<Route> results = new ArrayList<>();

        while (!pq.isEmpty() && results.size() < MAX_ROUTES) {
            Route current = pq.poll();
            Airport lastAirport = current.path.get(current.path.size() - 1);

            if (lastAirport.equals(dest)) {
                results.add(current);
                continue;
            }

            visitCounts.put(lastAirport, visitCounts.getOrDefault(lastAirport, 0) + 1);
            if (visitCounts.get(lastAirport) > 2) continue;

            for (FlightRoute route : graph.getRoutesFromAirport(lastAirport)) {
                if (!current.path.contains(route.getDestination())) {
                    List<Airport> newPath = new ArrayList<>(current.path);
                    newPath.add(route.getDestination());
                    double newCost = current.totalCost + route.getCurrentCost();
                    pq.add(new Route(newPath, newCost));
                }
            }
        }

        return results;
    }

    public static class Route {
        public List<Airport> path;
        public double totalCost;

        public Route(List<Airport> path, double totalCost) {
            this.path = path;
            this.totalCost = totalCost;
        }
    }
}