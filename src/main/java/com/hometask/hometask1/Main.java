package com.hometask.hometask1;

public class Main {
    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {0, 0, 0, 0, 1, 0, 0, 1, 1, 0},
                {1, 1, 0, 0, 1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 1, 1, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0}};

        int[][] arr1 = new int[][]{
                {0, 0, 0, 0, 1, 1, 0, 1, 1, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1},
                {0, 0, 1, 0, 0, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 1, 0, 0, 1, 1, 1},
                {0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 1, 1, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 0, 1, 0, 0, 0, 0}};
        System.out.println(getRectangleCount(arr));
        System.out.println(getRectangleCount(arr1));
    }

    private static int getRectangleCount(int[][] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 1
                        && ((i > 0 && a[i - 1][j] == 0) || i == 0)
                        && ((j > 0 && a[i][j - 1] == 0) || j == 0)) {
                    count++;
                }
            }
        }
        return count;
    }
}
