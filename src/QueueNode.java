/**
 * Created with IntelliJ IDEA
 * User: Vivien Keegan
 * Date: 7/16/15
 */
public class QueueNode {
    private int vertexNumber;
    private double distanceFromSource;

    public QueueNode(int num, double dist) {
        vertexNumber = num;
        distanceFromSource = dist;
    }

    public int getVertexNum() {
        return vertexNumber;
    }

    public double getDistance() {
        return distanceFromSource;
    }

    public String toString() {
        return "v = " + vertexNumber + ": " + distanceFromSource;
    }
}
