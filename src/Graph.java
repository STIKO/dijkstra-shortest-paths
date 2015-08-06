/**
 * Vivien Keegan
 * CSC 4520 Summer 2015
 * Programming Assignment
 */

import java.util.HashMap;
import java.util.HashSet;


public class Graph {
    private int order;
    private HashMap<Integer, HashSet<Neighbor>> adjacencyList;

    public Graph(int o, HashMap<Integer, HashSet<Neighbor>> adjList) {
        order = o;
        adjacencyList = adjList;
    }

    public int getOrder() {
        return order;
    }

    public HashSet<Neighbor> getNeighbors(int vertexNum) {
        return adjacencyList.get(vertexNum);
    }
}

