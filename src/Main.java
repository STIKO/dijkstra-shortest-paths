/*
   Vivien Keegan
   CSC 4520 Summer 2015
   Programming Assignment
   Dijkstra's Single-Source Shortest Paths Algorithm

   NOTE: Copies of the sample files are located in "resources/625349.txt"
         and "resources/10721073.txt". I renamed the files to make it easier to
         see the expected result while testing my code.
 */

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;


public class Main {

    public static void main(String[] args) throws IOException {
        final int SOURCE_VERTEX = 0;

        // Get input file from user
        Scanner inputScan = new Scanner(System.in);
        boolean enterInputFile;  // loop control

        do {
            System.out.print("Enter location of input file: ");
            File inputFile = new File(inputScan.nextLine().trim());

            if (inputFile.exists()) {
                //long startTime = System.nanoTime();

                Graph inputGraph = buildGraph(inputFile);
                System.out.println(getShortestPathsSum(inputGraph, SOURCE_VERTEX));

                //long endTime = System.nanoTime();
                //System.out.println((endTime - startTime) / 1000000000);

            } else {
                System.out.println("Sorry, that file cannot be found.");
            }

            System.out.print("Enter another file? (Y for Yes) ");
            String response = inputScan.nextLine().trim();

            enterInputFile = !response.isEmpty() && response.toUpperCase().charAt(0) == 'Y';
            System.out.println();
        } while (enterInputFile);
    }

    private static int getShortestPathsSum(Graph graph, int source) {
        double[] shortestPaths = goDijkstra(graph, source);

        double sum = 0;

        for (double distance : shortestPaths) {
            sum += distance;
        }

        return (int)sum;
    }

    // Build graph representation from file data
    private static Graph buildGraph(File file) throws IOException {
        // Load input file
        Scanner scan = new Scanner(file);

        // Parse number of vertices
        String graphOrder = scan.next(Pattern.compile("^n=\\d+"));
        int order = Integer.parseInt(graphOrder.substring(2));

        // Ignore number of edges, I don't seem to need it
        scan.nextLine();

        // Create adjacency list
        HashMap<Integer, HashSet<Neighbor>> adjacencyList = new HashMap<Integer, HashSet<Neighbor>>();

        // Initialize adjacency list for all vertices
        for (int i=0; i < order; i++) {
            adjacencyList.put(i, new HashSet<Neighbor>());
        }

        // Add neighbors
        while (scan.hasNextLine()) {
            int currentVertex = scan.nextInt();
            scan.nextLine();

            // Parse neighbors
            String nextLine = scan.nextLine().trim();
            HashSet<Neighbor> neighbors = adjacencyList.get(currentVertex);

            do {
                String[] neighborInput = nextLine.split("\\s+");
                int neighbor = Integer.parseInt(neighborInput[0]);
                int edgeWeight = Integer.parseInt(neighborInput[1]);

                neighbors.add(new Neighbor(neighbor, edgeWeight));
                adjacencyList.get(neighbor).add(new Neighbor(currentVertex, edgeWeight));

                nextLine = scan.nextLine().trim();
            } while (!nextLine.isEmpty());
        }

        scan.close();

        return new Graph(order, adjacencyList);
    }

    // Return shortest paths distances using Dijkstra's algorithm
    private static double[] goDijkstra(Graph graph, int source) {
        int order = graph.getOrder();

        MinPriorityQueue minPriorityQueue = new MinPriorityQueue(order);
        double[] finalDistances = new double[order];

        for (int i=0; i < finalDistances.length; i++) {
            finalDistances[i] = Double.POSITIVE_INFINITY;
        }

        // Initialize queue with source node
        minPriorityQueue.enqueue(new QueueNode(source, 0));

        while (!minPriorityQueue.isEmpty()) {
            QueueNode currentMin = minPriorityQueue.dequeue();

            int vertex = currentMin.getVertexNum();
            double distance = currentMin.getDistance();

            finalDistances[vertex] = distance;

            HashSet<Neighbor> neighbors = graph.getNeighbors(vertex);
            Iterator<Neighbor> neighborItrtr = neighbors.iterator();

            while (neighborItrtr.hasNext()) {
                Neighbor neighbor = neighborItrtr.next();
                int neighborNum = neighbor.getVertexNum();

                // if neighbor's final distance hasn't been computed
                if (finalDistances[neighborNum] == Double.POSITIVE_INFINITY) {
                    double currentDistance = neighbor.getEdgeWeight();

                    QueueNode neighborNode = new QueueNode(neighborNum, currentDistance+distance);

                    if (minPriorityQueue.contains(neighborNum)) {
                       minPriorityQueue.decreaseKey(neighborNode);
                    } else {
                        minPriorityQueue.enqueue(neighborNode);
                    }
                }
            }
        }

        return finalDistances;
    }
}