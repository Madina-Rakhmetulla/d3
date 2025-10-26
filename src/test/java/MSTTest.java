import org.junit.jupiter.api.*;
import models.*;
import algorithms.*;
import org.json.*;
import java.util.*;

public class MSTTest {
    @Test
    public void testPrimEqualsKruskal() {
        JSONArray nodes = new JSONArray(List.of("A", "B", "C", "D"));
        JSONArray edges = new JSONArray(List.of(
                new JSONObject().put("from", "A").put("to", "B").put("weight", 1),
                new JSONObject().put("from", "B").put("to", "C").put("weight", 2),
                new JSONObject().put("from", "C").put("to", "D").put("weight", 3),
                new JSONObject().put("from", "A").put("to", "D").put("weight", 10)
        ));
        Graph g = new Graph(nodes, edges);
        Prim.Result p = new Prim().run(g);
        Kruskal.Result k = new Kruskal().run(g);
        Assertions.assertEquals(p.totalCost, k.totalCost);
        Assertions.assertEquals(g.getNodes().size() - 1, p.mstEdges.size());
    }
}