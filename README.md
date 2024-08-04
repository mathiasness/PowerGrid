# Power Grid - Obligatory Assignment 2

## Project Topic

The topic of this project was to aid in the development of a power grid for the town of Flåklypa. In this assignment, there are three main tasks, all related to building and optimizing the power grid. 

## Project Implementation

In this project, I completed the implementation of the necessary algorithms to connect houses in the town to the power grid with the smallest cost possible, and developed strategies to handle potential power outages efficiently.

## Evaluation Criteria

All code submitted for this project was evaluated on four points:
- **Functional Correctness**: Ensuring the program performs as intended.
- **Runtime**: Evaluating the efficiency of the solution.
- **Runtime Analysis**: Adding comments to each method about its Big-O runtime.
- **Code Quality**: Ensuring the code is readable and maintainable.

**IMPORTANT**: When implementing the code, a runtime analysis was provided in [svar.md](svar.md).

## Scenario

A power company is building a power grid in the town of Flåklypa. All houses that need electricity must be connected to the power grid, and a plan must be in place in case of power outages. In this assignment, my job was to aid in the development of this power grid. The task was simplified by using only one type of power cable and assuming it does not matter how many houses are connected through the same cable.

## Tasks

### Task 1: Minimum Spanning Tree (MST)

The power company gathered information on all the houses that need to be connected to the grid. They mapped the cost of the cable between each pair of connected houses, modeled as a weighted graph. The goal was to construct the power grid with the smallest cost possible while supplying all houses with electricity.

**In this task, I implemented `IProblem::mst`**. This involved using algorithms to solve the Minimum Spanning Tree problem, as discussed in chapter 4.3 of *Algorithms*.

### Task 2: Power Outage Resilience

For this task, I ensured the power grid's resilience to power outages. The goal was to develop an algorithm that maintains electricity supply to as many houses as possible in the event of an outage.

### Task 3: Optimized Power Grid

In the final task, I optimized the power grid further, considering various factors and constraints to enhance efficiency and reliability.

## Efficiency Considerations

Correct and appropriate use of algorithms and data structures was crucial in this project. I utilized `LinkedList`, `ArrayList`, `HashMap`, `PriorityQueue`, etc., as needed. For each method, a Big-O runtime analysis was provided considering parameters like:
* `m` - number of edges in the graph
* `n` - number of nodes in the graph

### Code Quality

Code quality was assessed on the following points:
- The code must be clear and readable.
- Avoid repetition of code.
- Utilize concepts from INF101 to write maintainable and modular code.

### Runtime Analysis (svar.md)

Runtime analysis was assessed on the following points:
- Every method used must have a runtime analysis using Big-O notation.
- Points were given if the runtime was correct, but reduced if incorrect.
- The runtime analysis must be written in `svar.md` and include a description of why the method has this runtime.

### Functional and Efficient Algorithms and Data Structures

Correct and appropriate use of algorithms and data structures gave up to 9 points. Each task was assessed for functional correctness and efficiency:
- **Task 1**: Functional correctness and efficiency of methods and classes.
- **Task 2**: Functional correctness and efficiency of methods and classes.
- **Task 3**: Functional correctness and efficiency of methods and classes.

## Author
Task was completed by Mathias Hop Ness, with a pre existing code base from the Institute of Informatics at UIB.