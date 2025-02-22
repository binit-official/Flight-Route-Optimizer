package algorithms;

import java.util.Comparator;

public class RouteComparator implements Comparator<RouteFinder.Route> {

    @Override
    public int compare(RouteFinder.Route r1, RouteFinder.Route r2) {
        // Compare routes based on total cost (ascending order)
        return Double.compare(r1.totalCost, r2.totalCost);
    }
}