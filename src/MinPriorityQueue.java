/**
 * Created with IntelliJ IDEA
 * User: Vivien Keegan
 * Date: 7/18/15
 */

public class MinPriorityQueue {
    private MinBinaryHeap priorityQueue;

    public MinPriorityQueue(int capacity) {
        priorityQueue = new MinBinaryHeap(capacity);
    }

    public void enqueue(QueueNode node) {
        priorityQueue.add(node);
    }

    public QueueNode dequeue() {
        return priorityQueue.getMin();
    }

    public void decreaseKey(QueueNode node) {
        priorityQueue.decreaseKey(node);
    }

    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    public boolean contains(int vertexNum) {
        return priorityQueue.contains(vertexNum);
    }
}
