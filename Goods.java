package com.lut.java_system.game_package;

/**
 * @author Zhou
 * @desc
 * @date 2024/8/3 11:09
 */
public class Goods {
    private String name;//名称
    private String id;//编号
    private String type;//类型
    private int price;//价格
    private String description;//描述
    private boolean identify; // 是否鉴定
    public Goods(String name, String id,String type ,int price, String description) {
        this.name = name;
        this.id = id;
        this.type = type;
        this.price = price;
        this.description = description;
        this.identify=false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIdentify(){
        return identify;
    }

    public void setIdentify(){
        this.identify=true;
    }

    public void transaction() {

    }

    public void destroy() {

    }

}
