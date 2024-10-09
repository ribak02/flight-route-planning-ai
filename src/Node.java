import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node {
    int distance;
    int angle;
    Node parent;
    double gCost; // Cost from start to node
    double fCost;
    double hCost;
    int depth;
    boolean isLeaf;
    List<Node> forgottenChildren = new ArrayList<>();
    List<Node> children = new ArrayList<>(); // Track all children

    public Node(int distance, int angle, Node parent) {
        this.distance = distance;
        this.angle = angle;
        this.parent = parent;
        if (parent != null) {
            parent.children.add(this); // Automatically add this node to its parent's children list
        }
    }

    // Getter for fCost
    public double getFCost() {
        return this.fCost;
    }

    public void setFCost(double fCost) {
        this.fCost = fCost;
    }

    // Getter for fCost
    public double getHCost() {
        return this.hCost;
    }

    public void setHCost(double hCost) {
        this.hCost = hCost;
    }

    public double getGCost() {
        return gCost;
    }

    public void setGCost(double gCost) {
        this.gCost = gCost;
    }

    // Getter for distance
    public int getDistance() {
        return this.distance;
    }

    // Getter for angle
    public int getAngle() {
        return this.angle;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public boolean isLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    // Get the parent node
    public Node getParent() {
        return parent;
    }

    // Get the list of forgotten children
    public List<Node> getForgottenChildren() {
        return forgottenChildren;
    }

    public List<Node> getChildren() {
        return children;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Node node = (Node) obj;
        return distance == node.distance && angle == node.angle;
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, angle);
    }

    @Override
    public String toString() {
        return String.format("(%d:%d)", distance, angle);
    }
}
