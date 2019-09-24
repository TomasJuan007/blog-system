package com.ykse.blogs.service.impl.game;

import com.ykse.blogs.domain.MinePitModel;

import java.util.Random;

/**
 * What a coincidence to undermine on 9/11 :)
 *
 * Created by etomhua on 9/11/2017.
 */
public class MinesweeperServiceCommonImpl extends AbstractMinesweeperServiceImpl {

    @Override
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
        int missCount = 0;
        while(count<bound) {
            a = random.nextInt(size);
            b = random.nextInt(size);
            if (!map[a][b].isBomb()) {
                map[a][b].setBomb(true);
                count++;
            } else {
                missCount++;
            }
        }
        count+=missCount;
        return map;
    }
}
