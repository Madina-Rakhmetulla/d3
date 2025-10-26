package main;

import models.*;
import algorithms.*;
import org.json.*;
import java.nio.file.*;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            String jsonText = Files.readString(Path.of("src/main/resources/input.json"));
            JSONObject data = new JSONObject(jsonText);
            JSONArray graphs = data.getJSONArray("graphs");

            JSONArray results = new JSONArray();

            for (int i = 0; i < graphs.length(); i++) {
                JSONObject gObj = graphs.getJSONObject(i);
                Graph g = new Graph(gObj.getJSONArray("nodes"), gObj.getJSONArray("edges"));

                Prim.Result primRes = new Prim().run(g);
                Kruskal.Result kruskalRes = new Kruskal().run(g);

                JSONObject res = new JSONObject();
                res.put("graph_id", gObj.getInt("id"));
                JSONObject stats = new JSONObject();
                stats.put("vertices", g.getNodes().size());
                stats.put("edges", g.getEdges().size());
                res.put("input_stats", stats);
                res.put("prim", convertPrim(primRes));
                res.put("kruskal", convertKruskal(kruskalRes));

                results.put(res);
            }

            JSONObject output = new JSONObject();
            output.put("results", results);
            Files.writeString(Path.of("src/main/resources/output.json"), output.toString(2));

            System.out.println("✅ Results written to src/main/resources/output.json");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static JSONObject convertPrim(Prim.Result r) {
        JSONObject o = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Edge e : r.mstEdges) {
            JSONObject eo = new JSONObject();
            eo.put("from", e.getFrom());
            eo.put("to", e.getTo());
            eo.put("weight", e.getWeight());
            arr.put(eo);
        }
        o.put("mst_edges", arr);
        o.put("total_cost", r.totalCost);
        o.put("operations_count", r.operations);
        o.put("execution_time_ms", r.execTimeMs);
        return o;
    }

    private static JSONObject convertKruskal(Kruskal.Result r) {
        JSONObject o = new JSONObject();
        JSONArray arr = new JSONArray();
        for (Edge e : r.mstEdges) {
            JSONObject eo = new JSONObject();
            eo.put("from", e.getFrom());
            eo.put("to", e.getTo());
            eo.put("weight", e.getWeight());
            arr.put(eo);
        }
        o.put("mst_edges", arr);
        o.put("total_cost", r.totalCost);
        o.put("operations_count", r.operations);
        o.put("execution_time_ms", r.execTimeMs);
        return o;
    }
}