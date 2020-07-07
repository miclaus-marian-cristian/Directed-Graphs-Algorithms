package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import dataStructures.DGraph; 

public class MainProgram {

	public static void main(String[] args) {
		DGraph dGraph = new DGraph(5);
		dGraph.addEdge(0, 1, 2);
		dGraph.addEdge(0, 2, 4);
		dGraph.addEdge(1, 2, 1);
		dGraph.addEdge(1, 3, 8);
		dGraph.addEdge(1, 4, 4);
		dGraph.addEdge(2, 4, 3);
		dGraph.addEdge(3, 2, 3);
		dGraph.addEdge(4, 3, 2);
		dGraph.displayAdjList();
		//Scanner scanner = new Scanner(System.in);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String option = "";
		String answer = "n";
		do 
		{
			System.out.println("Press 1 for random total traversal.");
			System.out.println("Press 2 for breadth first total traversal.");
			System.out.println("Press 3 for depth first total traversal.");
			System.out.println("Press 4 to get the weakly connected components.");
			System.out.println("Press 5 to get the strongly connected components.");
			System.out.println("Press 6 for topological sort.");
			System.out.println("Press 7 for dijkstra algorithm.");
			//option = scanner.nextLine();
			try {
				option = reader.readLine();
				switch(option)
				{
					case "1":
						option1(dGraph);
						break;
					case "2":
						option2(dGraph);
						break;
					case "3":
						option3(dGraph);
						break;
					case "4":
						dGraph.findWeaklyConnectedComponents(0);
						break;
					case "5":
						dGraph.findStronglyConnectedComponents(0);
						break;
					case "6":
						LinkedList<Integer> tSortedList = new LinkedList<Integer>();
						dGraph.topologicalSort(tSortedList);
						System.out.println("Topologicaly sorted nodes: " + tSortedList);
						break;
					case "7":
						option7(dGraph);
						break;
					default:
						System.out.println("Inalid option!");
						break;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			

			System.out.println("Do you want to try again? (y/n)");
			//answer = scanner.nextLine();
			try {
				answer = reader.readLine();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}while(answer.equals("y"));
	}
	private static void option1(DGraph dGraph)
	{
		int size = dGraph.getSize();
		System.out.println("Random total traversal:");
		ArrayList<Integer> predecessors = new ArrayList<Integer>(size);
		ArrayList<Integer> order = new ArrayList<Integer>(size);//the order of traversal
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Insert the start node: ");
		String input = "";
		int start = 0;
		try {
			input = reader.readLine();
			try
			{
				start = Integer.parseInt(input);
			}
			catch(NumberFormatException e)
			{
				System.out.println(e.getMessage());
			}
			dGraph.randomTotalTraversal(start, predecessors, order);
			System.out.println(predecessors);
			System.out.println(order);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static void option2(DGraph dGraph) 
	{
		int size = dGraph.getSize();
		System.out.println("Total traversal breadth first:");
		//Stores the predecessor of each node
		ArrayList<Integer> predecessor = new ArrayList<Integer>(size);
		//stores the number of edges it takes to reach a node.
		ArrayList<Integer> edgeCount = new ArrayList<Integer>(size);
		int start = getFirstNodeInput();
		dGraph.totalTraversalBreadthFirst(start, predecessor, edgeCount);
		System.out.println("Predecessors list: " + predecessor);
		System.out.println("Edge count list: " + edgeCount);
	}
	private static void option3(DGraph dGraph) 
	{
		int size = dGraph.getSize();
		ArrayList<Integer> predecesorList= new ArrayList<Integer>(size);
		ArrayList<Integer> t1 = new ArrayList<Integer>(size);
		ArrayList<Integer> t2 = new ArrayList<Integer>(size);
		int start = getFirstNodeInput();
		dGraph.totalTraversalDepthFirst(start, predecesorList, t1, t2);
		System.out.println("Predecessors list: "+predecesorList);
		System.out.println("The order of visiting: "+t1);
		System.out.println("Fully analyzed nodes list timestamp: "+t2);
	}
	private static void option7(DGraph dGraph) 
	{
		ArrayList<Integer> predecesorList = new ArrayList<Integer>(dGraph.getSize());
		int start = getFirstNodeInput();
		dGraph.dijkstra(start, predecesorList);
		for(int i = 0; i < dGraph.getSize(); i++)
		{
			if(i == start)
				continue;
			dGraph.displayPathFromPredecessorsList(start, i, predecesorList);
			System.out.println();
		}
	}
	private static int getFirstNodeInput()
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String input = "";
		int start = 0;
		System.out.println("Insert the start node: ");
		try 
		{
			input = reader.readLine();
			try
			{
				start = Integer.parseInt(input);
			}
			catch(NumberFormatException e)
			{
				System.out.println(e.getMessage());
			}
		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		return start;
	}
}
