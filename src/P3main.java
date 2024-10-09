/********************
 * Starter Code
 * 
 * This class contains some examples of required inputs and outputs
 * 
 * @author alice toniolo
 *
 *         run with
 *         java P3main <Algo> <N> <ds:as> <dg:ag>
 *         we assume <N> <ds:as> <dg:ag> are valid parameters, no need to check
 */
public class P3main {
	public static void main(String[] args) {
		if (args.length < 4) {
			System.out.println("usage: java P3main <DFS|BFS|AStar|BestF|SMAStar|...> <N> <ds:as> <dg:ag> [<param>]");
			System.exit(1);
		}
		// Print initial information --- please do not delete
		System.out.println("World: Oedipus " + args[1]);
		System.out.println("Departure airport -- Start: " + args[2]);
		System.out.println("Destination airport -- Goal: " + args[3]);
		System.out.println("Search algorithm: " + args[0]);
		System.out.println();
		// end initial information

		String algo = args[0];
		int N = Integer.parseInt(args[1]);
		String[] startCoords = args[2].split(":");
		Node start = new Node(Integer.parseInt(startCoords[0]), Integer.parseInt(startCoords[1]), null);
		String[] goalCoords = args[3].split(":");
		Node goal = new Node(Integer.parseInt(goalCoords[0]), Integer.parseInt(goalCoords[1]), null);

		runSearch(algo, N, start, goal);
	}

	private static void runSearch(String algo, int size, Node start, Node goal) {
		switch (algo) {
			case "BFS": // run BFS
				PartA.searchBFS(size, start, goal);
				break;
			case "DFS": // run DFS
				PartA.searchDFS(size, start, goal);
				break;
			case "BestF": // run BestF
				BestFirst.searchBestFirst(size, start, goal);
				break;
			case "AStar": // run AStar
				AStar.searchAStar(size, start, goal);
				break;
			case "SMAStar": // run SMAStar
				SMAStar.searchSMAStar(size, start, goal, size);
				break;
		}

	}

}
