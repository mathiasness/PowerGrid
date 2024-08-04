# Answer File - Semester 2
# Description of each Implementation
Briefly describe your implementation of the different methods. What was your idea and how did you execute it? If there were any problems and/or failed implementations please add a description.

## Task 1 - mst
I based my solution on Prim's algorithm. I used a HashSet containing nodes for "found", as the time complexity for get and add is O(1). I used a PriorityQueue "toSearch" to sort edges with an add complexity of O(log m) and poll with O(log m). I went through every edge from smallest to biggest and added them to the mst list if they didn't create a cycle.

## Task 2 - lca
I created a helper-method which creates a "depth map". This HashMap contains nodes and their depth in the graph. Using this map, the method can find out which depth the two nodes are on. I then introduced another helper-method called getParent. This method find the neighbour of the given node which has a depth of currentDepth - 1. When the nodes are on the same depth, the method uses getParent on both nodes until the nodes are equal. This node is the lca.

## Task 3 - addRedundant
I found that the best strategy for minimizing the power outage was to add a redundant at the point where it creates the biggest cycle in the graph. There are two cases which decides where this redundant should be; If the graph only has one neighbour one of the nodes in the redundant edge should be the root, as a power outage in the edge between the root and this neighbour would cause the whole graph to lose power. The other case is when the root has multiple neighbours, in this case the method should look for the two biggest sub trees and traverse them to the end always looking for the node which has the biggest sub tree. To complete the task i made two helper-methods; subTreeSizeMap and traverseSubTree.


# Runtime Analysis
For each method of the different strategies give a runtime analysis in Big-O notation and a description of why it has this runtime.

**If you have implemented any helper methods you must add these as well.**

* Run time is also commented in the code
* When i refer to "parts" in the analysis, I am talking about the operations which have the biggest time complexities, loops etc. 

* ``mst(WeightedGraph<T, E> g)``: O(m log m)
    * The main parts of the method are a for-loop O(degree) and a while-loop O(m log m), O(degree) + O(m log m) = O(m log m). I used a PriorityQueue in order to always pick the smallest edge. poll() has a time complexity of O(log m) and add() has a time complexity of O(log m). The while-loop checks m-edges and every iteration potentially adds more edges with O(log m) complexity for each edge added, hence the total run time is O(m log m).
* ``lca(Graph<T> g, T root, T u, T v)``: O(n)
    * The first part of the method is calling the depthMap helper-method to create the depth map with a time complexity of O(n), as the worst case of this method is going through the whole tree before finding the target nodes. The method also consists of two while-loops which call the getParent method which has a time complexity of O(degree). These while loops have a time complexity of O(m) or O(n) since n*degree is m and m is equal to n-1 in a tree. Adding these together the total run time result of the method is O(n).
    * ``depthMap(Graph<V> g, V root, V targetNodeOne, V targetNodeTwo)``: O(n)
        * method maps nodes and their depth until it finds the targetnodes. In this method I used a LinkedList instead of a PriorityQueue beacause the order does not matter when searching and LinkedList has time complexity of O(1) for add and poll. Worst case the method has to check all nodes in the graph before finding the target nodes, hence total run time of O(n).
    * ``getParent(Graph<V> g, HashMap<V, Integer> depthMap, V node)`` : O(degree)
        * method finds a node's parent by comparing depth. It gets the nodes's depth from a HashMap O(1). The for-loop in the method checks the neighbours of the node looking for the node with a depth of the current node's depth - 1. 
* ``addRedundant(Graph<T> g, T root)``: O(n)
    * The main parts of the method are calling the subTreeSizeMap helper-method with a time complexity of O(n) and calling the traverseSubTree helper-method which also has a time complexity of O(n). Code explained above. The total run time is O(n) because O(n) + O(n) = O(n).
    * ``subTreeSizeMap(Graph<V> g, V root)``: O(n)
        * method returns a HashMap containing the each node in the graph and the size of their sub tree. This map is created by calling the recursive method recursiveStep which goes through every node in the tree and returns the size of the nodes itself and the amount of nodes below it in the tree. The recursive step method goes through every node with a total run time of O(n). Every other step in subTreeSizeMap is constant, which results in a total run time of O(n).
        * ``recursiveStep(Graph<V> g, Set<V> found, HashMap<V, Integer> subTreeMap, V node)``: O(n)
            * The method checks all the neighbours of the given node and calls itself recursively on all the children nodes until it finds a node where all the neighbours are already found as these nodes are endpoints in the tree. Total run time O(n).
    * ``traverseSubTree(Graph<V> g, HashMap<V, Integer> subTreeMap, V node)``: O(n)
        * Method picks the next node with the biggest subtree recursively until it reaches an endpoint where the sub tree size of the node is 1, this node is returned. Worst case the method has to go through every node in the graph to find this endpoint, hence a total run time of O(n).

