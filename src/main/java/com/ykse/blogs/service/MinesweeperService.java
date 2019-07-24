package com.ykse.blogs.service;

import com.ykse.blogs.service.impl.MinePitModel;

public interface MinesweeperService {
    MinePitModel[][] generateMap();
    void refreshMap(MinePitModel[][] map) throws Exception;
}
