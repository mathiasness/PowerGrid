package student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import graph.*;

public class ProblemSolver implements IProblem {

	@Override
	public <V, E extends Comparable<E>> ArrayList<Edge<V>> mst(WeightedGraph<V, E> g) { //O(m log m)
		Set<V> found = new HashSet<>(); //O(1)
		PriorityQueue<Edge<V>> toSearch = new PriorityQueue<Edge<V>>(g); //O(m)
		ArrayList<Edge<V>> mst = new ArrayList<>(); //O(1)

		V vertex = g.getFirstNode(); //O(1)
		found.add(vertex); //O(1)

		for (Edge<V> edge : g.adjacentEdges(vertex)) { //O(degree)
			toSearch.add(edge);
		}

		while (!toSearch.isEmpty()) { //OO(n * degree) = O(m) -> (m log m)
			Edge<V> edge = toSearch.poll(); //O(log m)

			if (!found.contains(edge.b)) { //O(1)
				found.add(edge.b); //O(1)
				mst.add(edge); //O(1)
				for (Edge<V> e : g.adjacentEdges(edge.b)) { //O(degree)
					if (found.contains(e.b)) continue;
					toSearch.add(e); //O(log m)
				}
			}			
		}

		return mst;
	}

	@Override
	public <V> V lca(Graph<V> g, V root, V u, V v) { //O(n)
		V lca = root; //O(1)
		if (u.equals(root) || v.equals(root)) return root; //O(1)

		HashMap<V, Integer> depthMap = depthMap(g, root, u, v); //O(n)
		Integer nodeUDepth = depthMap.get(u); //O(1)
		Integer nodeVDepth = depthMap.get(v); //O(1)

		while (nodeUDepth != nodeVDepth) { //O(n * degree) = O(n) or O(m) because a tree has n-1 edges
			if (nodeUDepth > nodeVDepth) {
				u = getParent(g, depthMap, u); //O(degree)
				nodeUDepth--; //O(1)
			} else {
				v = getParent(g, depthMap, v); //O(degree)
				nodeVDepth--; //O(1)
			}
		}

		while (!u.equals(v)) { //O(n * degree) = O(n) or O(m) because a tree has n-1 edges
			u = getParent(g, depthMap, u); //O(degree)
			v = getParent(g, depthMap, v); //O(degree)
		}
		lca = u; //O(1)

		return lca;
	}

	@Override
	public <V> Edge<V> addRedundant(Graph<V> g, V root) { //O(n)
		HashMap<V, Integer> subTreeMap = subTreeSizeMap(g, root); //O(n)
		V firstNode = root; //O(1)
		V secondNode = root; //O(1)		
		Integer firstSubTreeSize = 0; //O(1)
		Integer secondSubTreeSize = 0; //O(1)

		if (g.degree(root) == 1) {
			secondNode = traverseSubTree(g, subTreeMap, root); //O(n)
		} else {
			for (V child : g.neighbours(root)) { //O(degree)
				Integer childSize = subTreeMap.get(child); //O(1)
				if (childSize > firstSubTreeSize) { //O(1)
					secondSubTreeSize = firstSubTreeSize; //O(1)
					secondNode = firstNode; //O(1)
					
					firstSubTreeSize = childSize; //O(1)
					firstNode = child; //O(1)
				} else if (childSize > secondSubTreeSize) { //O(1)
					secondSubTreeSize = childSize; //O(1)
					secondNode = child; //O(1)
				}
			}
			firstNode = traverseSubTree(g, subTreeMap, firstNode); //O(n)
			secondNode = traverseSubTree(g, subTreeMap, secondNode); //O(n)
		}

		return new Edge<V>(firstNode, secondNode);
	}


	////////////////////////// HELPER METHODS //////////////////////////


	//LCA

	//method maps nodes and their depth until it finds the targetnodes
	//O(n) worst case, check the whole tree before finding target nodes
	private <V> HashMap<V, Integer> depthMap(Graph<V> g, V root, V targetNodeOne, V targetNodeTwo) { 
		HashMap<V, Integer> depthMap = new HashMap<>(); //O(1)
		Queue<V> toSearch = new LinkedList<>(); //O(1)

		V currentNode = root; //O(1)
		Integer currentDepth = 0; //O(1)
		depthMap.put(currentNode, currentDepth); //O(1)

		while (!depthMap.containsKey(targetNodeOne) || !depthMap.containsKey(targetNodeTwo)) { //O(n)
			for (V neighbour : g.neighbours(currentNode)) { //O(degree)
				if (!depthMap.containsKey(neighbour)) { //O(1)
					depthMap.put(neighbour, currentDepth + 1); //O(1)
					toSearch.add(neighbour); //O(1)
				}
			}
			currentNode = toSearch.poll(); //(O(1)
			currentDepth = depthMap.get(currentNode); //O(1)
		}

		return depthMap; 
	}

	//method finds a nodes parent by comparing depth
	//O(degree)
	private <V> V getParent(Graph<V> g, HashMap<V, Integer> depthMap, V node) {
		Integer nodeDepth = depthMap.get(node); //O(1)

		for (V neighbour : g.neighbours(node)) { //O(degree)
			if (!depthMap.containsKey(neighbour)) continue; //O(1)
			if (depthMap.get(neighbour) == nodeDepth - 1) { //O(1)
				return neighbour; //O(1)
			}
		}
		return node;
	}

	//REDUNDANT

	//method maps every node and their size as a sub tree
	//O(n) checks every node in the tree
	private <V> HashMap<V, Integer> subTreeSizeMap(Graph<V> g, V root) {
		HashMap<V, Integer> subTreeMap = new HashMap<>(); //O(1)
		Set<V> found = new HashSet<>(); //O(1)
		recursiveStep(g, found, subTreeMap, root); //O(n)
		return subTreeMap;
	}

	//recursive step for each node
	//O(n*degree) = O(m) or O(n) beacuse a tree has n-1 edges
	private <V> Integer recursiveStep(Graph<V> g, Set<V> found, HashMap<V, Integer> subTreeMap, V node) { 
		Integer nodeTreeSize = 1; //O(1)
		found.add(node); //O(1)
		for (V neighbour : g.neighbours(node)) { //O(degree)
			if (!found.contains(neighbour)) { //O(1)
				nodeTreeSize += recursiveStep(g, found, subTreeMap, neighbour); //O(n)
			}
		}
		subTreeMap.put(node, nodeTreeSize); //O(1)
		return nodeTreeSize;
	}

	//Method picks the next node with the biggest subtree until it reaches an end
	//O(n) worst case it has to traverse every node in the tree
	private <V> V traverseSubTree(Graph<V> g, HashMap<V, Integer> subTreeMap, V node) {
		Integer currentScore = subTreeMap.get(node); //O(1)
		if (currentScore == 1) return node; //O(1)

		Integer bestScore = 0; //O(1)
		V bestNode = node; //O(1)

		for (V child : g.neighbours(node)) { //O(degree)
			Integer childScore = subTreeMap.get(child); //O(1)
			if (childScore > currentScore) continue; //O(1)
			else if (childScore > bestScore) { //O(1)
				bestScore = childScore; //O(1)
				bestNode = child; //O(1)
			}
		}

		return traverseSubTree(g, subTreeMap, bestNode); //O(n * degree) = O(n) or O(m) because a tree has n-1 edges
	}
}
