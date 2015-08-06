/**
 * Created with IntelliJ IDEA
 * User: Vivien Keegan
 * Date: 7/15/15
 */

public class Neighbor {
    private int vertexNumber;
    private int edgeWeight;

    public Neighbor(int num, int dist) {
        vertexNumber = num;
        edgeWeight = dist;
    }

    public int getVertexNum() {
        return vertexNumber;
    }

    public int getEdgeWeight() {
        return edgeWeight;
    }

    public String toString() {
        return vertexNumber + " --- " + edgeWeight;
    }
}
