package dataStructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;

public class DGraph {
	private ArrayList<ArrayList<Node>> adjList;
	private int size;
	public DGraph(int nodesNumber) 
	{
		adjList = new ArrayList<ArrayList<Node>>(nodesNumber);
		size = nodesNumber;
		initDGraph();
	}
	public int getSize()
	{
		return size;
	}
	private void initDGraph()
	{
		for(int i = 0; i<size; i++)
			adjList.add(new ArrayList<Node>());
	}
	
	public void addEdge(Integer x, Integer y, Integer cost) {
		Node v = new Node(y, cost);
		adjList.get(x).add(v);
	}
	
	public void displayAdjList()
	{
		for(int i=0; i < size; i++)
		{
			System.out.print(i + ": ");
			ArrayList<Node> list = adjList.get(i);
			for(Node node : list)
			{
				System.out.print(node.getLabel()+",");
			}
			System.out.println();
		}
	}
	
	public void randomTotalTraversal(int start, ArrayList<Integer> pred, ArrayList<Integer> ord)
	{
		LinkedList<Integer> visited = new LinkedList<Integer>();
		LinkedList<Integer> unvisited = new LinkedList<Integer>();
		ArrayList<Integer> wVisited = new ArrayList<Integer>();
		int k = 1;
		for(int i=0; i<adjList.size(); i++)
		{
			pred.add(-1);
			ord.add(0);
		}
		ord.set(start, 1);
	
		for(int i = 0; i<size; i++)
		{
			if(i == start)
				continue;
			
			unvisited.add(i);
		}
		visited.add(start);
		
		while(wVisited.size() != adjList.size())
		{
			Random rand = new Random();
			while(visited.size() > 0)
			{
				int index = rand.nextInt(visited.size());
				int x = visited.get(index);
				ArrayList<Node> list = adjList.get(x);
				boolean found = false;
				boolean isEmpty = list.isEmpty();
				if(!isEmpty)
				{
					for(Node v : list)
					{
						if(unvisited.contains(v.getLabel()))
						{
							unvisited.remove(new Integer(v.getLabel()));
							visited.add(v.getLabel());
							pred.set(v.getLabel(), x);
							k += 1;
							ord.set(v.getLabel(), k);
							found = true;
							break;
						}
					}
					if(!found || isEmpty)
					{
						visited.remove(new Integer(x));
						wVisited.add(x);
					}
				}
			}
			if(unvisited.size()>0)
			{
				int index = rand.nextInt(unvisited.size());
				start = unvisited.get(index);
				k += 1;
				ord.set(start, k);
				unvisited.remove(new Integer(start));
				visited.add(start);
			}
		}
	}

	public void totalTraversalBreadthFirst(int start, ArrayList<Integer> predecessor, ArrayList<Integer> edgeCount)
	{
		LinkedList<Integer> visited = new LinkedList<Integer>();
		LinkedList<Integer> unvisited = new LinkedList<Integer>();
		LinkedList<Integer> wVisited = new LinkedList<Integer>();
		for(int i=0; i<size ;i++)
		{
			predecessor.add(-1);
			edgeCount.add(0);
		}
		edgeCount.set(start, 0);
		
		for(int i = 0; i < size; i++)
		{
			if(i == start)
				continue;
			
			unvisited.add(i);
		}
		visited.add(start);
		while(wVisited.size() != adjList.size())
		{
			Random rand = new Random();
			while(visited.size() > 0)
			{
				int x = visited.get(0);
				ArrayList<Node> list = adjList.get(x);
				
				if(!list.isEmpty())
				{
					for(Node v : list)
					{
						if(unvisited.contains(v.getLabel()))
						{
							unvisited.remove(new Integer(v.getLabel()));
							visited.add(v.getLabel());
							predecessor.set(v.getLabel(), x);
							edgeCount.set(v.getLabel(), edgeCount.get(x)+1);
						}
					}
				}
				visited.remove(new Integer(x));
				wVisited.add(x);
			}
			if(unvisited.size() > 0)
			{
				int index = rand.nextInt(unvisited.size());
				start = unvisited.get(index);
				unvisited.remove(new Integer(start));
				visited.add(start);
				edgeCount.set(start, 0);
			}
		}
	}
	
	public void totalTraversalDepthFirst(int start, ArrayList<Integer> predecesorsList, ArrayList<Integer> t1, ArrayList<Integer> t2)
	{
		LinkedList<Integer> visited = new LinkedList<Integer>();
		LinkedList<Integer> unvisited = new LinkedList<Integer>();
		ArrayList<Integer> wVisited = new ArrayList<Integer>();
		int t = 1;
		for(int i=0; i<adjList.size();i++)
		{
			predecesorsList.add(-1);
			t1.add(0);
			t2.add(0);
		}
		t1.set(start, 1);
	
		for(int i=0; i<size; i++)
		{
			if(i == start)
				continue;
			
			unvisited.add(i);
		}
		visited.add(start);
		
		while(wVisited.size() != size)
		{
			Random rand = new Random();
			while(visited.size() > 0)
			{
				int x = visited.get(visited.size()-1);//LIFO
				ArrayList<Node> list = adjList.get(x);
				boolean found = false;
				boolean isEmpty = list.isEmpty();
				if(isEmpty == false)
				{
					for(Node v : list)
					{
						if(unvisited.contains(v.getLabel()))
						{
							unvisited.remove(new Integer(v.getLabel()));
							visited.add(v.getLabel());
							predecesorsList.set(v.getLabel(), x);
							t += 1;
							t1.set(v.getLabel(), t);
							found = true;
							break;
						}
					}
				}
				if(!found || isEmpty == true)
				{
					visited.remove(new Integer(x));
					wVisited.add(x);
					t += 1;
					t2.set(x, t);
				}
			}
			if(unvisited.size()>0)
			{
				int index = rand.nextInt(unvisited.size());
				start = unvisited.get(index);
				t += 1;
				t1.set(start, t);
				unvisited.remove(new Integer(start));
				visited.add(start);
			}
		}

	}
	
	public void findWeaklyConnectedComponents(int start)
	{
		LinkedList<Integer> visited = new LinkedList<Integer>();
		LinkedList<Integer> unvisited = new LinkedList<Integer>();
		ArrayList<Integer> wVisited = new ArrayList<Integer>();
		HashSet<Integer> nPrime = new HashSet<Integer>();
		int nc=1;
		for(int i = 0; i < size; i++)
		{
			if(i == start)
				continue;
			
			unvisited.add(i);
		}
		visited.add(start);
		nPrime.add(start);
		while(wVisited.size() != adjList.size())
		{
			Random rand = new Random();
			while(visited.size() > 0)
			{
				int x = visited.get(visited.size()-1);
				ArrayList<Node> list = adjList.get(x);
				boolean found = false;
				if(unvisited.size() > 0) {
					if(list.isEmpty() == false)
					{
						for(Node v : list)
						{
							if(unvisited.contains(v.getLabel()))
							{
								unvisited.remove(new Integer(v.getLabel()));
								visited.add(v.getLabel());
								nPrime.add(v.getLabel());
								found = true;
								break;
							}
						}
					}
					if(!found)//search an edge (y,x) where y is unvisited.
					{	
						for(int y : unvisited) {
							ArrayList<Node> list1 = adjList.get(y);
							for(Node v1 : list1) {
								if(v1.getLabel() == x) {
									unvisited.remove(new Integer(y));
									visited.add(y);
									nPrime.add(y);
									found = true;
									break;
								}
							}
							if(found)
								break;
						}
						if(!found) {
							visited.remove(new Integer(x));
							wVisited.add(x);
						}
					}
				}else {
					visited.remove(new Integer(x));
					wVisited.add(x);
				}
			}
			System.out.print("Component "+nc+ ": ");
			System.out.println(nPrime);
			if(unvisited.size() > 0) {
				int index = rand.nextInt(unvisited.size());
				start = unvisited.get(index);
				unvisited.remove(new Integer(start));
				visited.add(start);
				nc +=1;
				nPrime.clear();
				nPrime.add(start);
			}
		}
	}
	
	public void findStronglyConnectedComponents(int start)
	{
		HashSet<Integer> nPrim = new HashSet<Integer>();
		ArrayList<Integer> predList = new ArrayList<Integer>(size);
		ArrayList<Integer> t1 = new ArrayList<Integer>(size);
		ArrayList<Integer> t2 = new ArrayList<Integer>(size);
		totalTraversalDepthFirst(start, predList, t1, t2);
		ArrayList<ArrayList<Node>> invAdjList = inverseGraph();
		LinkedList<Integer> unvisited = new LinkedList<Integer>();
		LinkedList<Integer> visited = new LinkedList<Integer>();
		for(int x = 0; x < size; x++)
		{
			unvisited.add(x);
		}
		while(unvisited.size() > 0){
			int pos = getPosWithMaxValueFromt2(unvisited, t2);
			int s = unvisited.get(pos);//starting node
			visited.add(s);
			unvisited.remove(new Integer(s));
			nPrim.add(s);
			while(visited.size()>0) {
				int x = visited.get(visited.size()-1);
				ArrayList<Node> list = invAdjList.get(x);
				boolean found = false;
				if(list.isEmpty() == false){
					for(Node v : list){
						//check to see if I can find an adj. node with x from the unvisited nodes
						if(unvisited.contains(v.getLabel())){
							unvisited.remove(new Integer(v.getLabel()));
							visited.add(v.getLabel());
							nPrim.add(v.getLabel());
							found = true;
							break;
						}
					}
				}
				if(!found || list.isEmpty()) {
					visited.remove(new Integer(x));
				}
			}
			System.out.println(nPrim);
			nPrim.clear();
		}
	}
	//returns the node's position with the greatest value from t2
	private int getPosWithMaxValueFromt2(LinkedList<Integer> unvisited, ArrayList<Integer> t2)
	{
		if(!unvisited.isEmpty() && !t2.isEmpty()) {
			int pos=0;
			//take first node from unvisited and calculate its position in t2
			int index = unvisited.get(0);
			int max = t2.get(index);
			for(int i = 1; i < unvisited.size(); i++) {
				index = unvisited.get(i);
				if(max < t2.get(index)) {
					max = t2.get(index);
					pos = i;
				}
			}
			return pos;
		}
		return -1;
	}
	
	private ArrayList<ArrayList<Node>> inverseGraph()
	{
		ArrayList<ArrayList<Node>> invAdjList = new ArrayList<ArrayList<Node>>(size);
		for(int i = 0; i < size; i++)
			invAdjList.add(new ArrayList<Node>());
		
		for(int x = 0; x < size; x++) 
		{
			ArrayList<Node> list = adjList.get(x);
			for(Node y :list ) 
			{
				invAdjList.get(y.getLabel()).add(new Node(x));
			}
		}
		return invAdjList;
	}
	
	public void topologicalSort(LinkedList<Integer> topologicalSortedList)
	{
		LinkedList<Integer> visited = new LinkedList<Integer>();
		LinkedList<Integer> unvisited = new LinkedList<Integer>();
		ArrayList<Integer> t1 = new ArrayList<Integer>();
		ArrayList<Integer> t2 = new ArrayList<Integer>();
		LinkedList<Integer> nodesForStart = new LinkedList<Integer>();//nodes with pminus(x)=0
		ArrayList<ArrayList<Node>> invAdjList = inverseGraph();
		int start = 0;
		//check to see which nodes has pminus(x) = 0;
		for(int x = 0; x < size; x++)
		{
			ArrayList<Node> list = invAdjList.get(x);
			if(list.isEmpty()) {
				nodesForStart.add(x);
			}
		}
		if(!nodesForStart.isEmpty()) {
			start = nodesForStart.getFirst();
			visited.add(start);
			int t = 1;
			nodesForStart.removeFirst();
			for(int x = 0; x < size; x++) {
				if(x == start)
					continue;
				unvisited.add(x);
			}
			for(int i =0; i < size; i++) {
				t1.add(0); t2.add(0);
			}
			t1.set(start, t);
			while(unvisited.size() > 0) {
				while(visited.size() > 0) {
					int x = visited.getLast();
					if(unvisited.size() > 0) {
						ArrayList<Node> list = adjList.get(x);
						boolean found = false;
						if(!list.isEmpty()) {
							for(Node v : list) {
								if(unvisited.contains(v.getLabel())) {
									unvisited.remove(new Integer(v.getLabel()));
									visited.add(v.getLabel());
									t++;
									t1.set(v.getLabel(), t);
									found = true;
									break;
								}
								if(found)
									break;
							}
						}
						if(!found) {
							visited.remove(new Integer(x));
							topologicalSortedList.addFirst(x);
							t++;
							t2.set(x, t);
						}
					}
					else {
						visited.remove(new Integer(x));
						topologicalSortedList.addFirst(x);
						t++;
						t2.set(x, t);
					}
				}
				if(nodesForStart.size() > 0)
				{
					start = nodesForStart.getFirst();
					nodesForStart.removeFirst();
					visited.add(start);
					unvisited.remove(new Integer(start));
					t++; t1.set(start, t);
				}
			}
		}
	}

	public void dijkstra(int start, ArrayList<Integer> predList)
	{
		LinkedList<Integer> unvisited = new LinkedList<Integer>();
		ArrayList<Integer> dist = new ArrayList<Integer>();
		for(int i = 0; i < size; i++)
		{
			unvisited.add(i);
			dist.add(Integer.MAX_VALUE);
			predList.add(-1);
		}
		dist.set(start,0);
		while(unvisited.size() > 0) 
		{
			int cost = Integer.MAX_VALUE;
			int x = 0;
			//choose the node with the lowest cost from unvisited
			for(int node : unvisited) 
			{
				if(cost > dist.get(node))
				{
					cost = dist.get(node);
					x = node;
				}
			}
			unvisited.remove(new Integer(x));
			ArrayList<Node> xList = adjList.get(x);
			//check the edges[x, y] with y being unvisited.
			for(int y : unvisited) {
				if(xList.contains(new Node(y))) {
					int ind = xList.indexOf(new Node(y));
					Node v = xList.get(ind);//this node stores the distance from x to y
					if( (dist.get(x) + v.getCost() ) < dist.get(y)) {
						dist.set(y, dist.get(x)+v.getCost());
						predList.set(y, x);
					}
				}
			}
		}
		System.out.println("distances: "+ dist);
		System.out.println("Predecessors List: "+predList);
	}
	
	public void displayPathFromPredecessorsList(int s, int y, ArrayList<Integer> predecessors)
	{
		if(y==s)
			System.out.print(s+", ");
		else
		{
			if(predecessors.get(y) == -1) 
				System.out.println("There is no path from "+s+" to "+y);
			else 
			{
				displayPathFromPredecessorsList(s, predecessors.get(y), predecessors);
				System.out.print(y+",");
			}
		}
	}
	
}
