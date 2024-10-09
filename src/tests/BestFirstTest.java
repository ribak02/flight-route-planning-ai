package tests;

import org.junit.Before;
import org.junit.Test;

import Node;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BestFirstTest {
    Node start;
    Node goal;
    ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        // Initialize nodes with realistic connections for Best First Search
        start = new Node(0, 0, null); // Start node at origin
        goal = new Node(2, 0, null); // Goal node at a distance of 2

        // Manually set up the heuristic values and successors based on actual Util
        // methods
        // Link children to simulate a grid or graph where nodes are connected logically
        start.setGCost(0);
        start.setHCost(Util.heuristic(start, goal));
        start.setFCost(start.getGCost() + start.getHCost());

        // Redirect System.out to capture outputs for assertion
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testSearchBestFirst_Success() {
        // Here, nodes should be connected in a way that Best First Search can succeed.
        Node middle = new Node(1, 0, start);
        middle.setGCost(Util.calculateMoveCost(start, middle));
        middle.setHCost(Util.heuristic(middle, goal));
        middle.setFCost(middle.getGCost() + middle.getHCost());

        start.getChildren().add(middle);
        middle.getChildren().add(goal);

        BestFirst.searchBestFirst(3, start, goal);

        // Check if the output indicates the search has found the goal
        String output = outContent.toString();
        assertTrue(output.contains(goal.toString()));
        assertTrue(output.contains(String.format("%.3f", middle.getGCost() + Util.calculateMoveCost(middle, goal))));
        assertTrue(output.contains("3")); // Assumes node count in output matches the path taken
    }

    @Test
    public void testSearchBestFirst_Fail() {
        // Setting up a scenario where no path is available to the goal

        BestFirst.searchBestFirst(3, start, new Node(0, 2, null));

        System.out.println(outContent.toString());
        // Check if the output indicates the search has failed
        assertTrue(outContent.toString().contains("fail"));
        assertTrue(outContent.toString().contains("1")); // Only the start node is visited
    }
}
