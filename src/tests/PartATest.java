package tests;

import org.junit.Before;
import org.junit.Test;

import Node;

import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PartATest {
    Node start;
    Node goal;
    ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        start = new Node(2, 45, null);
        goal = new Node(2, 0, null);
        Node n2 = new Node(2, 90, start);
        Node n3 = new Node(3, 45, start);
        Node n1 = new Node(1, 45, start);
        Node n1_alt = new Node(1, 0, n1);
        Node n1_alt2 = new Node(1, 90, n1);

        start.children.add(goal); // Direct connection for simplicity
        start.children.add(n2);
        start.children.add(n3);
        start.children.add(n1);
        n1.children.add(n1_alt);
        n1.children.add(n1_alt2);

        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testBFS_Found() {
        PartA.searchBFS(5, start, goal);
        String output = outContent.toString();
        assertTrue(output.contains("(2:45)(2:0)"));
        assertTrue(output.contains("1.571"));
        assertTrue(output.contains("3"));
    }

    @Test
    public void testDFS_Found() {
        PartA.searchDFS(5, start, goal);
        String output = outContent.toString();
        assertTrue(output.contains("(2:45)(2:0)"));
        assertTrue(output.contains("1.571"));
        assertTrue(output.contains("3"));
    }

    @Test
    public void testDFS_NotFound() {
        Node unreachable = new Node(4, 90, null); // No connection to the graph
        PartA.searchDFS(3, start, unreachable);
        assertTrue(outContent.toString().contains("fail"));
    }

    @Test
    public void testBFS_NotFound() {
        Node unreachable = new Node(4, 90, null); // No connection to the graph
        PartA.searchBFS(3, start, unreachable);
        assertTrue(outContent.toString().contains("fail"));
    }
}
