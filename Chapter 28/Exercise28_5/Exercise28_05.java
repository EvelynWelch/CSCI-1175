// Author: Evie Welch
// date: 04/04/23

import java.util.*;

import java.io.*;

public class Exercise28_05 {
	public static void main(String[] args) throws Exception {
		String[] vertices = { 
				"Seattle", // 0
				"San Francisco", // 1 
				"Los Angeles", // 2
				"Denver", // 3
				"Kansas City", // 4 
				"Chicago", // 5
				"Boston", // 6
				"New York", // 7
				"Atlanta", // 8
				"Miami", // 9
				"Dallas",  // 10
				"Houston" // 11
				};

		int[][] edges = { 
				{ 0, 1 }, { 0, 3 }, { 0, 5 }, 
				{ 1, 0 }, { 1, 2 }, { 1, 3 }, 
				{ 2, 1 }, { 2, 3 }, { 2, 4 }, { 2, 10 }, 
				{ 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 4 }, { 3, 5 }, 
				{ 4, 2 }, { 4, 3 }, { 4, 5 }, { 4, 7 }, { 4, 8 }, { 4, 10 }, 
				{ 5, 0 }, { 5, 3 }, { 5, 4 }, { 5, 6 }, { 5, 7 },
				{ 6, 5 }, { 6, 7 }, 
				{ 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 8 }, 
				{ 8, 4 }, { 8, 7 }, { 8, 9 }, { 8, 10 }, { 8, 11 }, 
				{ 9, 8 }, { 9, 11 },
				{ 10, 2 }, { 10, 4 }, { 10, 8 }, { 10, 11 }, 
				{ 11, 8 }, { 11, 9 }, { 11, 10 } 
			};

		UnweightedGraph<String> graph = new UnweightedGraph<>(
				vertices, edges);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a starting city: ");
		String startingCity = input.nextLine();
//		String startingCity = "Houston";
		System.out.print("Enter an ending city: ");
		String endingCity = input.nextLine();
//		String endingCity =  "Los Angeles";

		List<Integer> list = graph.getPath(graph.getIndex(startingCity),
				graph.getIndex(endingCity));

		System.out.print("The path is ");
		for (Integer i : list) {
			System.out.print(graph.getVertex(i) + " ");
		}
	}
}