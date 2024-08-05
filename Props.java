package com.lut.java_system.game_package;

/**
 * @author Zhou
 * @desc 道具类
 * @date 2024/8/3 12:37
 */
public class Props extends Goods{
    private String property;//道具属性

    public Props(String name, String id, String type, int price, String description,String property) {
        super(name, id, type, price, description);
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }
}
