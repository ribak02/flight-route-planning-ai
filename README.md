# flight-route-planning-ai
AI search algorithms to solve the flight route planning problem in a fictional 2D polar coordinate. The primary goal is to find the most efficient route

## 1. Introduction
The objective of this project is to implement and evaluate various AI search algorithms to solve the simplified flight route planning problem in a fictional 2D polar coordinate system on the planet Oedipus. The primary goal is to find the most efficient route from a designated starting point (departure airport) to a destination point (arrival airport).

### Problem Components
The main components of the search problem in the flight route finder include:
- **State space**: Each point on the planet Oedipus is represented as a polar coordinate (d: angle), where d is the distance from the pole, and the angle is in degrees (from 0 to 315 in increments of 45 degrees).
- **Start and goal states**: Defined by their polar coordinates, with S as the start and G as the goal.
- **Actions**: Movement between adjacent coordinates constrained by the planet's grid system.
- **Cost**: Typically uniform, with the cost of moving from one point to an adjacent point set to 1 unit.

### Completed Work
All search algorithms from Part A and Part B were fully implemented, tested, and evaluated. However, SMAStar is not fully functioning due to cost calculation or max nodes memory failures. The algorithm's main design and implementation were completed, but further work is needed.

---

## 2. Design and Implementation
The search strategies implemented in the flight route planner are broadly categorized into **uninformed** and **informed search algorithms**, each serving different purposes based on the availability of heuristic information.

### Uninformed Search Algorithms
- **Breadth-First Search (BFS)**: Explores the search space level by level and guarantees the shortest path in unweighted graphs.
- **Depth-First Search (DFS)**: Explores deep into the search space before backtracking but does not guarantee the shortest path.

### Informed Search Algorithms
- **Best-first Search (BestF)**: Uses a priority queue to expand the most promising node based on a heuristic function.
- **A* Search (AStar)**: Combines BFS and BestF by using both the cost to reach a node and a heuristic estimate.
- **Simplified Memory-bounded A* (SMAStar)**: Modifies A* by imposing memory limits, discarding the least promising nodes when memory is full.

---

## 3. Testing
Each algorithm was rigorously tested across various scenarios defined in the project appendix, such as differing planet sizes and varied starting and goal locations. Automated tests were conducted using the `stacscheck` framework. JUnit tests were written for all algorithms in Part A and Part C, including helper and utility functions.

---

## 4. Evaluation

### Number of Nodes Visited (Efficiency)
The "Nodes Visited" graph shows the number of nodes each algorithm processed before reaching a solution. Fewer nodes visited typically suggest higher search efficiency.

![image](https://github.com/user-attachments/assets/23c56ca7-b78c-4f56-8744-5a04e28f52f6)


### Path Cost (Effectiveness)
The "Path Cost" graph measures the quality of the path found by each algorithm, with lower costs indicating more optimal solutions.

![image](https://github.com/user-attachments/assets/ed29c03e-ec1b-4b42-ac89-a14933977773)


### Path Cost to Nodes Visited Analysis
The "Path Cost to Nodes Visited" graph presents a comparative analysis of two key performance metrics: path cost and the number of nodes visited.

![image](https://github.com/user-attachments/assets/6b6c906f-866f-4db7-8b75-36057d682f35)

---

## 5. Conclusion
This project demonstrated the effectiveness of informed search algorithms in handling complex route planning tasks. Best-first and AStar were particularly effective in balancing search efficiency and path optimality. The results underscore the value of heuristic-driven algorithms in efficiently navigating large search spaces while producing high-quality solutions.
