package com.ykse.blogs.service.impl.game;

import com.ykse.blogs.domain.MinePitModel;

import java.util.Random;

/**
 * What a coincidence to undermine on 9/11 :)
 *
 * Created by etomhua on 9/11/2017.
 */
public class MinesweeperServiceKnuthImpl extends AbstractMinesweeperServiceImpl {

    @Override
    public MinePitModel[][] generateMap() {
        int bombCount = 0;
        MinePitModel[] pitArray = new MinePitModel[size*size];
        for (int i=0;i<size*size;i++) {
            MinePitModel pit = new MinePitModel();
            pit.setLat(i/size);
            pit.setLng(i%size);
            if (bombCount<bound) {
                pit.setBomb(true);
                bombCount++;
            }
            pitArray[i] = pit;
        }

        Random randomT = new Random();

        for (int i=size*size-1; i>=0; i--) {
            swap(pitArray, i, (int)Math.floor(randomT.nextDouble()*i));
            count++;
        }

        for (int i=0; i<size*size; i++) {
            map[i/size][i%size] = pitArray[i];
        }

        return map;
    }

    private void swap(MinePitModel[] map, int i, int rand) {
        MinePitModel pit = map[i];
        map[i] = map[rand];
        map[rand] = pit;
    }

    @Override
    public void refreshMap(MinePitModel[][] map) throws Exception {
        super.refreshMap(map);
    }

}
