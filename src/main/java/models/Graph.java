package models;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;

public class Graph {
    private final List<String> nodes;
    private final List<Edge> edges;

    public Graph(JSONArray nodesArray, JSONArray edgesArray) {
        nodes = new ArrayList<>();
        for (int i = 0; i < nodesArray.length(); i++)
            nodes.add(nodesArray.getString(i));

        edges = new ArrayList<>();
        for (int i = 0; i < edgesArray.length(); i++) {
            JSONObject e = edgesArray.getJSONObject(i);
            edges.add(new Edge(e.getString("from"),
                    e.getString("to"),
                    e.getInt("weight")));
        }
    }

    public List<String> getNodes() { return nodes; }
    public List<Edge> getEdges() { return edges; }

    public Map<String, List<Edge>> getAdjList() {
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String v : nodes) adj.put(v, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.getFrom()).add(e);
            adj.get(e.getTo()).add(new Edge(e.getTo(), e.getFrom(), e.getWeight()));
        }
        return adj;
    }
}