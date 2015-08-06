/**
 * Created with IntelliJ IDEA
 * User: Vivien Keegan
 * Date: 7/18/15
 */

public class MinBinaryHeap {
    private final int NOT_IN_HEAP = -1;

    private QueueNode[] minHeap;
    private int[] heapKeys;
    private int size;

    public MinBinaryHeap(int capacity) {
        minHeap = new QueueNode[capacity];
        heapKeys = new int[capacity];

        // Heap starts out empty
        for (int i=0; i < heapKeys.length; i++) {
            heapKeys[i] = NOT_IN_HEAP;
        }

        size = 0;
    }

    public void add(QueueNode node) {
        int position = size;
        heapifyUp(position, node);
        size++;
    }

    public QueueNode getMin() {
        int root = 0;
        int last = size-1;

        // Remove root
        QueueNode min = minHeap[root];
        heapKeys[min.getVertexNum()] = NOT_IN_HEAP;

        heapifyDown(root, minHeap[last]);
        size--;
        return min;
    }

    // Decrease key of node already in the heap
    public void decreaseKey(QueueNode node) {
        int vertex = node.getVertexNum();
        int keyInHeap = heapKeys[vertex];

        if (node.getDistance() < minHeap[keyInHeap].getDistance()) {
            heapifyUp(keyInHeap, node);
        }
    }

    // Place node in given position
    private void placeNode(QueueNode node, int position) {
        minHeap[position] = node;

        int vertex = minHeap[position].getVertexNum();
        heapKeys[vertex] = position;
    }

    // Heapify up starting from given position
    private void heapifyUp(int current, QueueNode nodeToPlace) {
        if (current == 0) {
            placeNode(nodeToPlace, current);
            return;
        }

        int parent = (current - 1) / 2;

        if (nodeToPlace.getDistance() < minHeap[parent].getDistance()) {
            placeNode(minHeap[parent], current);
            heapifyUp(parent, nodeToPlace);
        } else {
            placeNode(nodeToPlace, current);
        }
    }

    // Heapify down starting from given position
    private void heapifyDown(int current, QueueNode nodeToPlace) {
        if (isLeaf(current)) {
            placeNode(nodeToPlace, current);
            return;
        }

        int leftChild = 2 * current + 1;
        int minChild = leftChild;

        if (hasRightChild(current)) {
            int rightChild = leftChild + 1;
            minChild = minHeap[leftChild].getDistance() < minHeap[rightChild].getDistance() ? leftChild : rightChild;
        }

        if (nodeToPlace.getDistance() > minHeap[minChild].getDistance()) {
            placeNode(minHeap[minChild], current);
            heapifyDown(minChild, nodeToPlace);
        } else {
            placeNode(nodeToPlace, current);
        }
    }

    // Return true if node has no children
    private boolean isLeaf(int nodeKey) {
        return (2 * nodeKey + 1 > size - 1);
    }

    // Return true if node has a right child
    public boolean hasRightChild(int nodeKey) {
        return (2 * nodeKey + 2 <= size - 1);
    }

    // Return true if there are no elements
    public boolean isEmpty() {
        return (size == 0);
    }

    // Return true if vertex is in heap
    public boolean contains(int vertexNum) {
        return (heapKeys[vertexNum] > NOT_IN_HEAP);
    }
}
