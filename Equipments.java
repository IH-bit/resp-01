package com.lut.java_system.game_package;


/**
 * @author Zhou
 * @desc 装备类
 * @date 2024/8/3 12:37
 */
public class Equipments extends Goods{
    private int rank; // 装备等级
    private int quality; // 装备品质


    public Equipments(String name, String id, String type, int price, String description,int rank,int quality) {
        super(name, id, type, price, description);
        this.rank = rank;
        this.quality = quality;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

}
