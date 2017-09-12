package com.ykse.blogs.service.impl;

import java.util.Random;

/**
 * What a coincidence to undermine on 9/11 :)
 *
 * Created by etomhua on 9/11/2017.
 */
public class MinesweeperServiceImpl {
    private int size = 16;
    private MinePitModel[][] map = new MinePitModel[size][size];
    private int count = 0;
    private int bound = (int) (16*16/6.4);

    public MinePitModel[][] generateMap() {
        for (int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                MinePitModel pit = new MinePitModel();
                pit.setLat(i);
                pit.setLng(j);
                map[i][j] = pit;
            }
        }
        Random random = new Random();
        int a,b;
        while(count<bound) {
            a = random.nextInt(size);
            b = random.nextInt(size);
            if (!map[a][b].isBomb()) {
                map[a][b].setBomb(true);
                count++;
            }
        }
        return map;
    }

    public void refreshMap(MinePitModel[][] map) throws Exception {
        if(map.length!=size || map[0].length!=size) throw new Exception();
        for (int i=0;i<size;i++) {
            for (int j = 0; j < size; j++) {
                MinePitModel pit = map[i][j];
                if(pit.isBomb())
                    reportBombsToNeighbor(i,j);
            }
        }
    }

    private void reportBombsToNeighbor(int i, int j) {
        addCount(i-1,j-1);
        addCount(i-1,j);
        addCount(i-1,j+1);
        addCount(i,j-1);
        addCount(i,j+1);
        addCount(i+1,j-1);
        addCount(i+1,j);
        addCount(i+1,j+1);
    }

    private void addCount(int i, int j) {
        if(i>=0&&i<size&&j>=0&&j<size) {
            MinePitModel pit = map[i][j];
            pit.setBombsAround(pit.getBombsAround()+1);
            map[i][j] = pit;
        }
    }

    public MinePitModel stamp(int lat, int lng) {
        return map[lat][lng];
    }

    public static void main(String[] args) {
        MinesweeperServiceImpl m = new MinesweeperServiceImpl();
        MinePitModel[][] result = m.generateMap();
        //draw the map
        int num = 0;
        for (int i=0;i<16;i++) {
            for (int j=0;j<16;j++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
        System.out.println("The number of the generated bombs is: "+m.count);
        //play the bombs
        try {
            m.refreshMap(result);
        }catch (Exception e) {
            System.out.println("For your own good, pls don't mess with the BOMBS!");
            e.printStackTrace();
        }
        //stamp lat2,lng2
        String output;
        if(result[1][1].isBomb()) {
            output = "*";
        }else{
            output = String.valueOf(result[1][1].getBombsAround());
        }
        System.out.println("line2 row2: "+output);
    }
}
