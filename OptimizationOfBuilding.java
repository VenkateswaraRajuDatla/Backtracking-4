//time 2 pow HW
//space o(HW)

import java.io.*;

import java.util.LinkedList;

import java.util.Queue;


public class GFG {

	public static void main (String[] args) {

		BuildingPlacement buildingPlacement = new BuildingPlacement();

        System.out.print(buildingPlacement.findMinDistance(4, 4, 1)); 

	}

	public static class BuildingPlacement {

	   int minDistance = Integer.MAX_VALUE;

	   public int findMinDistance(int H, int W, int n){

	       int[][] grid = new int[H][W];
	       for(int i=0; i<H; i++) {
	           for(int j=0; j<W; j++) {
	               grid[i][j] = -1;
	           }
	       }
	       
	       backtrack(grid, 0, 0, n, H, W);
	       return minDistance;
	   }

	   // breadth first for finding minDistance of each combination

	   private void bfs(int [][] grid, int H, int W){
	       Queue<int[]> q = new LinkedList<>();
	       boolean [][] visited = new boolean[H][W];
	       
	       for(int i=0; i<H; i++) {
	           for(int j=0; j<W; j++) {
	               if(grid[i][j] == 0) {
	                   q.add(new int[]{i, j});
	                   visited[i][j] = true;
	               }
	           }
	       }
	       
	       int[][] dirs = {{1,0}, {-1,0}, {0,-1}, {0,1}};
	       int distance = 0;
	       while(!q.isEmpty()) {
	           int size = q.size();
	           for(int i=0; i<size; i++) {
	               int[] cur = q.poll();
	               for(int[] dir: dirs) {
	                   int r = cur[0] + dir[0];
	                   int c = cur[1] + dir[1];
	                   if(r>=0 && c>=0 && r<H && c<W && !visited[r][c]) {
	                       q.add(new int[]{r, c});
	                       visited[r][c] = true;
	                   } 
	               }
	           }
	           distance++;
	       }
	       minDistance = Math.min(minDistance, distance-1);
	   }

	   private void backtrack(int [][] grid, int r, int c, int n, int H, int W){
	       //base case
	       if(n == 0) {
	           bfs(grid, H, W);
	           return;
	       }
	       //logic
	       if(c >= W) {
	           r++;
	           c=0;
	       }
	       for(int i=r; i<H; i++) {
	           for(int j=c; j<W; j++) {
	               //action
	               grid[i][j] = 0;
	               //recurse
	               backtrack(grid, i, j+1, n-1, H, W);
	               //backtrack
	               grid[i][j] = -1;
	           }
	           c=0;
	       }
	   }

	}

}