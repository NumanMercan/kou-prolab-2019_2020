import java.util.*;

public class RouteFinder
{
	private final City[] cities;

	public RouteFinder(City[] cities)
	{
		this.cities = cities;
	}

	public Route findRoute(City from, City to)
	{
		Queue<RouteCity> openSet = new PriorityQueue<>();
		Map<City, RouteCity> allNodes = new HashMap<>();

		RouteCity start = new RouteCity(from, null, 0d, City.getCost(from, to));
		openSet.add(start);
		allNodes.put(from, start);

		while (!openSet.isEmpty())
		{
			RouteCity next = openSet.poll();
			if (next.getCurrent().equals(to))
			{
				Route route = new Route();
				RouteCity current = next;
				route.cost = current.getRouteScore();
				do
				{
					route.cities.add(0, current.getCurrent());
					current = allNodes.get(current.getPrevious());
				} while (current != null);
				return route;
			}

			for (int plateNum : next.getCurrent().getConnected())
			{
				City connection = cities[plateNum - 1];
				RouteCity nextNode = allNodes.getOrDefault(connection, new RouteCity(connection));
				allNodes.put(connection, nextNode);

				double newScore = next.getRouteScore() + City.getCost(next.getCurrent(), connection);
				if (newScore < nextNode.getRouteScore())
				{
					nextNode.setPrevious(next.getCurrent());
					nextNode.setRouteScore(newScore);
					nextNode.setEstimatedScore(newScore + City.getCost(connection, to));
					openSet.add(nextNode);
				}
			}
		}

		throw new IllegalStateException("No route found");
	}

	public Route findMultiRoute(ArrayList<City> destinations)
	{
		destinations.add(0, cities[40]);
		ArrayList<City> visited = new ArrayList<>();
		City current = destinations.get(0);

		while (destinations.size() > 0)
		{
			destinations.remove(current);

			double closestCost = Double.POSITIVE_INFINITY;
			City closest = null;
			for (City x : destinations)
			{
				Route route = this.findRoute(current, x);
				if (route.cost < closestCost)
				{
					closestCost = route.cost;
					closest = x;
				}
			}
			visited.add(current);
			current = closest;
		}

		visited.add(cities[40]);

		Route totalRoute = new Route();
		for (int i = 0; i < visited.size() - 1; i++)
		{
			Route route = this.findRoute(visited.get(i), visited.get(i + 1));

			// tüm parça rotaları birleştiriyoruz
			if (i > 0)
				route.cities = route.cities.subList(1, route.cities.size());

			totalRoute.cities.addAll(route.cities);
			totalRoute.cost += route.cost;
		}

		return totalRoute;
	}
}
