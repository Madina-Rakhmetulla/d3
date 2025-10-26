package algorithms;

import models.*;
import java.util.*;

public class Prim {
    public static class Result {
        public List<Edge> mstEdges;
        public int totalCost;
        public int operations;
        public double execTimeMs;

        public Result(List<Edge> e, int c, int op, double t) {
            mstEdges = e;
            totalCost = c;
            operations = op;
            execTimeMs = t;
        }
    }

    public Result run(Graph g) {
        Map<String, List<Edge>> adj = g.getAdjList();
        Set<String> visited = new HashSet<>();
        List<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int cost = 0, ops = 0;

        long start = System.nanoTime();

        String startNode = g.getNodes().get(0);
        visited.add(startNode);
        pq.addAll(adj.get(startNode));

        while (!pq.isEmpty() && visited.size() < g.getNodes().size()) {
            Edge e = pq.poll();
            ops++;
            if (visited.contains(e.getTo())) continue;
            visited.add(e.getTo());
            mst.add(e);
            cost += e.getWeight();
            for (Edge next : adj.get(e.getTo()))
                if (!visited.contains(next.getTo())) pq.add(next);
        }

        double ms = (System.nanoTime() - start) / 1e6;
        return new Result(mst, cost, ops, ms);
    }
}