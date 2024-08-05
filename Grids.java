package com.lut.java_system.game_package;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhou
 * @desc 格子类
 * @date 2024/8/3 12:35
 */
public class Grids {
    private Goods goods;
    private int number;
    // 查询所有物品
    public Grids() {
        this.goods=null;
        this.number=0;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
