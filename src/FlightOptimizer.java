import model.Airport;
import graph.FlightGraph;
import algorithms.RouteFinder;
import utils.DataLoader;
import java.util.Scanner;
import java.util.*;

public class FlightOptimizer {
    public static void main(String[] args) {
        try {
            FlightGraph graph = DataLoader.loadGraph(
                    "data/global_airports.csv",
                    "data/global_routes.csv"
            );

            Scanner scanner = new Scanner(System.in);
            System.out.println("Available Airport Codes: DEL, BOM, LHR, JFK, DXB, SIN, etc.");

            System.out.print("Enter Source Airport Code: ");
            Airport source = getAirport(graph, scanner.nextLine());

            System.out.print("Enter Destination Airport Code: ");
            Airport dest = getAirport(graph, scanner.nextLine());

            List<RouteFinder.Route> routes = RouteFinder.findTopRoutes(graph, source, dest);

            if(routes.isEmpty()) {
                System.out.println("\nNo routes found between these airports!");
                return;
            }

            System.out.println("\nTop 3 Recommended Routes:");
            for(int i=0; i<routes.size(); i++) {
                RouteFinder.Route route = routes.get(i);
                System.out.printf("\nRoute %d (Cost: $%.2f)%n", i+1, route.totalCost);
                route.path.forEach(airport ->
                        System.out.println("  → " + airport.getName() + " (" + airport.getId() + ")"));
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static Airport getAirport(FlightGraph graph, String code) {
        return graph.getAllAirports().stream()
                .filter(a -> a.getId().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid airport code: " + code));
    }
}