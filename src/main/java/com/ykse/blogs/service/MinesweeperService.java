package com.ykse.blogs.service;

import com.ykse.blogs.domain.MinePitModel;

public interface MinesweeperService {
    MinePitModel[][] generateMap();
    void refreshMap(MinePitModel[][] map) throws Exception;
}
