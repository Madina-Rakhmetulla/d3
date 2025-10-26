package algorithms;

import models.*;
import java.util.*;

public class Kruskal {
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

    private static class DSU {
        private final Map<String, String> parent = new HashMap<>();
        private final Map<String, Integer> rank = new HashMap<>();

        public DSU(List<String> v) {
            for (String s : v) {
                parent.put(s, s);
                rank.put(s, 0);
            }
        }

        public String find(String x) {
            if (!parent.get(x).equals(x))
                parent.put(x, find(parent.get(x)));
            return parent.get(x);
        }

        public void union(String a, String b) {
            String ra = find(a), rb = find(b);
            if (ra.equals(rb)) return;
            if (rank.get(ra) < rank.get(rb)) parent.put(ra, rb);
            else if (rank.get(ra) > rank.get(rb)) parent.put(rb, ra);
            else { parent.put(rb, ra); rank.put(ra, rank.get(ra)+1); }
        }
    }

    public Result run(Graph g) {
        List<Edge> sorted = new ArrayList<>(g.getEdges());
        Collections.sort(sorted);
        DSU dsu = new DSU(g.getNodes());
        List<Edge> mst = new ArrayList<>();
        int cost = 0, ops = 0;

        long start = System.nanoTime();
        for (Edge e : sorted) {
            ops++;
            String u = e.getFrom(), v = e.getTo();
            if (!dsu.find(u).equals(dsu.find(v))) {
                dsu.union(u, v);
                mst.add(e);
                cost += e.getWeight();
            }
        }
        double ms = (System.nanoTime() - start) / 1e6;
        return new Result(mst, cost, ops, ms);
    }
}