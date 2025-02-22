package utils;

import model.Airport;
import model.FlightRoute;
import graph.FlightGraph;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class DataLoader {
    public static FlightGraph loadGraph(String airportsFile, String routesFile) throws FileNotFoundException {
        FlightGraph graph = new FlightGraph();
        Map<String, Airport> airports = new HashMap<>();

        // 1. Load airports first
        Scanner scanner = new Scanner(new File(airportsFile));
        scanner.nextLine(); // Skip header
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            String id = data[0].trim();
            Airport airport = new Airport(
                    id,
                    data[1].trim(),
                    Double.parseDouble(data[2]),
                    Double.parseDouble(data[3])
            );
            airports.put(id, airport);
            graph.addAirport(airport); // Add to graph immediately
        }
        scanner.close();

        // 2. Load routes (skip invalid ones)
        scanner = new Scanner(new File(routesFile));
        scanner.nextLine(); // Skip header
        while (scanner.hasNextLine()) {
            String[] data = scanner.nextLine().split(",");
            String srcId = data[0].trim();
            String destId = data[1].trim();
            double distance = Double.parseDouble(data[2].trim());

            Airport source = airports.get(srcId);
            Airport dest = airports.get(destId);

            // Skip routes with invalid airports
            if (source == null || dest == null) {
                System.err.println("Skipping invalid route: " + srcId + " -> " + destId);
                continue;
            }

            graph.addRoute(new FlightRoute(source, dest, distance));
        }
        scanner.close();

        return graph;
    }
}