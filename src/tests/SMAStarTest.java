package tests;
// import org.junit.Before;
// import org.junit.Test;
// import static org.junit.Assert.*;
// import java.io.ByteArrayOutputStream;
// import java.io.PrintStream;
// import java.util.Arrays;

// public class SMAStarTest {
// Node start;
// Node goal;
// ByteArrayOutputStream outContent;

// @Before
// public void setUp() {
// start = new Node(0, 0, null); // Assume Node(int distance, int angle, Node
// parent)
// goal = new Node(3, 0, null); // Goal further away to test memory bounds

// // Redirect System.out to capture outputs for assertion
// outContent = new ByteArrayOutputStream();
// System.setOut(new PrintStream(outContent));
// }

// @Test
// public void testSearchSMAStar_Success() {
// // Setup a path that fits within memory limits
// Node middle1 = new Node(1, 0, start);
// Node middle2 = new Node(2, 0, middle1);

// SMAStar.searchSMAStar(4, start, goal, 4); // Memory limit larger than
// necessary path length

// // Check if the output includes the goal node string representation
// String output = outContent.toString();
// assertTrue(output.contains("3")); // Assumes node count in output matches the
// path taken
// }

// @Test
// public void testSearchSMAStar_MemoryLimitReached() {
// // Test memory limits triggering node forgetting
// Node close = new Node(1, 0, start);
// Node far = new Node(1, 90, start);
// Node farther = new Node(2, 90, far);

// start.getChildren().addAll(Arrays.asList(close, far));
// far.getChildren().add(farther);
// farther.getChildren().add(goal); // Ensures goal is connected but requires
// pruning to reach

// SMAStar.searchSMAStar(4, start, goal, 3); // Memory limit forces pruning

// // Check outputs for indications of pruning and eventual failure or success
// String output = outContent.toString();
// assertTrue(output.contains("fail")); // Check if pruning leads to failure
// assertFalse(output.contains(goal.toString())); // Goal should not be reached
// if pruning is correct
// }
// }
