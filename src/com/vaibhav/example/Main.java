package com.vaibhav.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

class Point{
    int i;
    int j;

    public Point(int i, int j) {
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;
        Point point = (Point) o;
        return getI() == point.getI() &&
                getJ() == point.getJ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getI(), getJ());
    }
}

public class Main {

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        int numOfIslands = numberOfIsLands(grid);
        System.out.println(numOfIslands);
    }

    private static int numberOfIsLands(char[][] grid) {
        if(grid.length == 0){
            return 0;
        }

        boolean [][] visited = new boolean[grid.length][grid[0].length];

        int count = 0;

        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == '1' && !visited[i][j]){
                    doDFS(grid, visited, i , j);
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean isValidPosition(char[][] grid, boolean[][] visited, int i, int j){
        if(i < grid.length && j < grid[0].length && i >= 0 && j >= 0 && visited[i][j] == false && grid[i][j] == '1'){
            return true;
        }
        return false;
    }

    public static List<Point> getNeighbors(char[][] grid, boolean[][] visited, int i, int j){
        List<Point> points = new ArrayList<>();
        if(isValidPosition(grid, visited, i-1, j)){
            points.add(new Point(i-1, j));
        }
        if(isValidPosition(grid, visited, i+1, j)){
            points.add(new Point(i+1, j));
        }
        if(isValidPosition(grid, visited, i, j-1)){
            points.add(new Point(i, j-1));
        }
        if(isValidPosition(grid, visited, i, j+1)){
            points.add(new Point(i, j+1));
        }

        return points;
    }

    private static void doDFS(char[][] grid, boolean[][] visited, int i, int j) {
                visited[i][j] = true;
                List<Point> neighbors = getNeighbors(grid, visited, i, j);
                for(Point p : neighbors){
                    if(!visited[p.getI()][p.getJ()]){
                        visited[p.getI()][p.getJ()] = true;
                        doDFS(grid, visited, p.getI(), p.getJ());
                    }
            }
    }
}
