package tests;

import org.junit.Test;

import Node;
import Util;

import static org.junit.Assert.*;
import java.util.List;

public class UtilTest {

    @Test
    public void testGetSuccessors_BasicScenario() {
        Node node = new Node(2, 90, null);
        List<Node> successors = Util.getSuccessors(node, 5);

        // Expecting North, East, South, West movements
        assertEquals(4, successors.size());
        assertTrue(successors.contains(new Node(1, 90, node))); // North
        assertTrue(successors.contains(new Node(2, 135, node))); // East
        assertTrue(successors.contains(new Node(3, 90, node))); // South
        assertTrue(successors.contains(new Node(2, 45, node))); // West
    }

    @Test
    public void testGetSuccessors_AtPoles() {
        Node nodeAtNorthPole = new Node(0, 0, null);
        List<Node> successors = Util.getSuccessors(nodeAtNorthPole, 3);

        // Should only allow East and South movements
        assertEquals(3, successors.size());
        assertTrue(successors.contains(new Node(1, 0, nodeAtNorthPole))); // South
        assertTrue(successors.contains(new Node(0, 45, nodeAtNorthPole))); // East

        Node nodeAtSouthPole = new Node(2, 0, null);
        successors = Util.getSuccessors(nodeAtSouthPole, 3);
        // Should only allow North and East movements
        assertEquals(3, successors.size());
        assertTrue(successors.contains(new Node(1, 0, nodeAtSouthPole))); // North
        assertTrue(successors.contains(new Node(2, 45, nodeAtSouthPole))); // East
    }

    @Test
    public void testCalculateMoveCost_EastWest() {
        Node current = new Node(3, 0, null);
        Node targetEast = new Node(3, 45, current);
        assertEquals(Math.PI * 3 / 4, Util.calculateMoveCost(current, targetEast), 0.001);
    }

    @Test
    public void testCalculateMoveCost_NorthSouth() {
        Node current = new Node(2, 90, null);
        Node targetNorth = new Node(1, 90, current);
        assertEquals(1.0, Util.calculateMoveCost(current, targetNorth), 0.0);
    }

    @Test
    public void testHeuristic() {
        Node current = new Node(3, 0, null);
        Node goal = new Node(5, 90, null);
        double expectedDistance = Math.sqrt(3 * 3 + 5 * 5 - 2 * 3 * 5 * Math.cos(Math.toRadians(90)));
        assertEquals(expectedDistance, Util.heuristic(current, goal), 0.001);
    }
}
