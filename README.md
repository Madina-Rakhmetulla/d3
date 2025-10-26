#  Assignment 3 — Minimum Spanning Tree (MST)

##  Objective
The goal of this assignment is to **implement and compare two classic algorithms** — **Prim’s** and **Kruskal’s** — for finding the **Minimum Spanning Tree (MST)** of weighted connected graphs.

The comparison is based on:
- Total MST cost  
- Number of operations performed  
- Execution time (in milliseconds)

---

## ⚙️ Technologies Used
- **Language:** Java 17  
- **Framework:** Maven  
- **IDE:** IntelliJ IDEA  
- **Libraries:**  
  - `org.json` — for reading and writing JSON files  
  - `JUnit 5` — for testing

---

##  Project Structure
MST_Project/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── algorithms/
│   │   │   │   ├── Prim.java
│   │   │   │   └── Kruskal.java
│   │   │   ├── models/
│   │   │   │   ├── Graph.java
│   │   │   │   └── Edge.java
│   │   │   └── Main.java
│   │   └── resources/
│   │       ├── input.json
│   │       └── output.json
│   └── test/
│       └── java/
│           └── MSTTest.java
└── README.md

---

##  Input and Output Format

### **Input (`input.json`):**
Contains multiple graphs (small, medium, large) represented by:
- `id` — graph number  
- `nodes` — list of vertex labels  
- `edges` — connections with weights

Example:
```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C"],
      "edges": [
        {"from": "A", "to": "B", "weight": 3},
        {"from": "B", "to": "C", "weight": 4},
        {"from": "A", "to": "C", "weight": 5}
      ]
    }
  ]
}

Output (output.json):

Generated automatically after running Main.java.
Contains:
	•	Total MST cost
	•	Number of operations
	•	Execution time
	•	List of edges in MST

Example:
{
  "results": [
    {
      "graph_id": 1,
      "input_stats": {"vertices": 3, "edges": 3},
      "prim": {
        "total_cost": 7,
        "operations_count": 15,
        "execution_time_ms": 0.93
      },
      "kruskal": {
        "total_cost": 7,
        "operations_count": 12,
        "execution_time_ms": 0.81
      }
    }
  ]
}

How to Run the Project

Option 1 — Using IntelliJ IDEA
	1.	Open the project folder (MST_Project) in IntelliJ.
	2.	Ensure JDK 17 is selected under
File → Project Structure → Project SDK.
	3.	Load Maven dependencies (click Load Maven Changes if prompted).
	4.	Open Main.java → right-click → Run ‘Main.main()’.
	5.	Output will be saved in:

src/main/resources/output.json

Option 2 — Using Command Line
mvn clean compile
mvn exec:java -Dexec.mainClass="main.Main"

Testing (JUnit 5)

The test file MSTTest.java verifies:
	•	Prim’s and Kruskal’s algorithms produce the same total cost.
	•	The MST contains exactly n − 1 edges.

Run tests in IntelliJ:
Right-click on MSTTest.java → Run 'MSTTest'

or via terminal:

mvn test

 Example Results

| Graph | Vertices | Edges | Prim Cost | Kruskal Cost | Prim Ops | Kruskal Ops | Prim Time (ms) | Kruskal Time (ms) |
|––––|———–|––––|———––|—————|———––|––––––––|—————––|
| Small  | 5 | 6 | 16 | 16 | 34 | 28 | 1.3 | 1.1 |
| Medium | 10 | 14 | 34 | 34 | 95 | 81 | 3.4 | 2.9 |
| Large  | 20 | 25 | 62 | 62 | 210 | 187 | 7.5 | 6.7 |

⸻

 Algorithm Overview

Prim’s Algorithm
	•	Starts from any vertex.
	•	Uses a priority queue to pick the smallest edge that connects a new vertex.
	•	Efficient for dense graphs.

Kruskal’s Algorithm
	•	Sorts all edges by weight.
	•	Adds edges one by one, skipping cycles (using Disjoint Set Union).
	•	Efficient for sparse graphs.

⸻

 Analysis Summary
	•	Both algorithms produce identical MST cost.
	•	Kruskal performs fewer operations on sparse graphs.
	•	Prim is faster on dense graphs.
	•	Execution time grows linearly with number of edges.

⸻

 References
	1.	Cormen, T. H. et al. Introduction to Algorithms (3rd Edition). MIT Press, 2009.
	2.	GeeksForGeeks. Prim’s and Kruskal’s Algorithms for MST.
	3.	Oracle Java Documentation – PriorityQueue, Collections, HashMap.
	4.	Eclipse Adoptium Temurin 17 – OpenJDK Distribution.

⸻

Author

Rakhmetulla Madina
Astana IT University
Design and Analysis of Algorithms — Assignment 3
