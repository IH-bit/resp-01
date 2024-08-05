package com.lut.java_system.game_package;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Zhou
 * @desc 背包仓库类
 * @date 2024/8/3 12:34
 */
public class KnapsackStorage{
    private int capacity=30; // 背包容量
    private ArrayList<Grids> gridsList; // 格子列表
    private Grids grids; // 格子
    private Wallet wallet; // 钱包

    public KnapsackStorage() {
        gridsList = new ArrayList<>();
        for(int i=0;i<capacity;i++){
            gridsList.add(new Grids());
        }
        wallet = new Wallet();
    }
    public void start() {
        // 启动游戏
        while (true) {
            Object[] options = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            int result = JOptionPane.showOptionDialog(null,
                    "请选择你的操作:\n1. 查询所有物品\n2. 查询指定物品\n3. 查询钱包金额\n4. 鉴定指定物品\n5. 添加指定物品\n6. 销毁指定物品\n7. 交易指定物品\n8. 背包整理\n9. 背包扩容\n10. 退出游戏", "操作页面",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null, options, options[9]);
            switch (result) {
                case 0:
                    // 查询所有物品
                    queryAllGoods();
                    break;
                case 1:
                    // 查询指定物品
                    queryGoods();
                    break;
                case 2:
                    // 查询钱包金额
                    queryWallet();
                    break;
                case 3:
                    // 鉴定指定物品
                    identifyGoods();
                    break;
                case 4:
                    // 添加指定物品
                    String nameOrId=JOptionPane.showInputDialog("请输入物品名称或编号: ");
                    int number=Integer.parseInt(JOptionPane.showInputDialog("请输入数量: "));
                    addGoods(nameOrId,number);
                    break;
                case 5:
                    // 销毁指定物品
                    destroyGoods();
                    break;
                case 6:
                    // 交易指定物品
                    tradeGoods();
                    break;
                case 7:
                    // 背包整理
                    bagClean();
                    break;
                case 8:
                    // 背包扩容
                    expandBag();
                    break;
                case 9:
                    // 退出游戏
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
    // 查询所有物品
    public void queryAllGoods(){
        // 构建要显示的消息文本
        StringBuilder message = new StringBuilder();
        for(int i=0;i<gridsList.size();i++){
            if(gridsList.get(i).getGoods()!=null){
                String type=gridsList.get(i).getGoods().getType();
                message.append(i+1).append(GoodsList.getGoodsInfo(gridsList.get(i).getGoods().getName())).append(" 数量：")
                        .append(gridsList.get(i).getNumber()).append("\n");
            }else{
                message.append(i+1).append("空闲状态").append("\n");
            }
        }
        Object[] options1 = {"确认", "返回"};
        JOptionPane.showOptionDialog(null, message.toString(), "物品栏", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null,options1, options1[0]);
    }
    //查询指定物品
    public void queryGoods(){
        String nameOrId=JOptionPane.showInputDialog("请输入你要查询的物品名称或编号: ");
        Boolean flag=true;
        for(Grids g:gridsList){
            if(g.getGoods().getName().equals(nameOrId) || g.getGoods().getId().equals(nameOrId)) {
                flag=false;
                Object[] options1 = {"确认", "返回"};
                JOptionPane.showOptionDialog(null, GoodsList.getGoodsInfo(g.getGoods().getName()), "物品", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, options1[0]);
                break;
            }
        }
        if(flag){
            JOptionPane.showMessageDialog(null,"没有找到该物品！","提示",JOptionPane.ERROR_MESSAGE);
        }
    }
    //添加指定物品
    public void addGoods(String nameOrId,int number){
        for(int i=0;i<gridsList.size();i++){
            if(gridsList.get(i).getGoods()!=null){
                if(gridsList.get(i).getGoods().getName().equals(nameOrId)&&gridsList.get(i).getNumber()+number<=5||gridsList.get(i).getGoods().getId().equals(nameOrId)&&gridsList.get(i).getNumber()+number<=5){
                    gridsList.get(i).setNumber(gridsList.get(i).getNumber()+number);
                    JOptionPane.showMessageDialog(null,"添加成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }else if(gridsList.get(i).getGoods().getName().equals(nameOrId)&&gridsList.get(i).getNumber()+number>5||gridsList.get(i).getGoods().getId().equals(nameOrId)&&gridsList.get(i).getNumber()+number>5){
                    int num=gridsList.get(i).getNumber();
                    gridsList.get(i).setNumber(5);
                    gridsList.get(i+1).setGoods(getGoodsById(nameOrId));
                    gridsList.get(i+1).setNumber(number-(5-num));
                    JOptionPane.showMessageDialog(null,"添加成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }else if(gridsList.get(i).getGoods()==null){
                gridsList.get(i).setGoods(getGoodsById(nameOrId));
                gridsList.get(i).setNumber(number);
                JOptionPane.showMessageDialog(null,"添加成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                break;
            }else{
                JOptionPane.showMessageDialog(null,"背包已满！","提示",JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
    }
    //查询钱包金额
    public void queryWallet(){
        JOptionPane.showMessageDialog(null,"当前钱包余额为："+wallet.getBalance(),"钱包",JOptionPane.INFORMATION_MESSAGE);
    }
    //鉴定指定物品
    public void identifyGoods() {
        String nameOrId = JOptionPane.showInputDialog("请输入你要鉴定的物品名称或编号: ");
        Boolean flag = true;
        for (Grids g : gridsList) {
            if (g.getGoods()!=null&&g.getGoods().getType().equals("装备")&&(g.getGoods().getName().equals(nameOrId) || g.getGoods().getId().equals(nameOrId))) {
                flag = false;
                if(!g.getGoods().getIdentify()){
                    g.getGoods().setIdentify();
                    JOptionPane.showMessageDialog(null,"鉴定成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }else{
                    JOptionPane.showMessageDialog(null,"该物品已鉴定！","提示",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
        }
        if (flag) {
            JOptionPane.showMessageDialog(null, "该物品不可鉴定或者没有找到该物品！", "提示", JOptionPane.ERROR_MESSAGE);
        }
    }
    //销毁指定物品
    public void destroyGoods() {
        String nameOrId = JOptionPane.showInputDialog("请输入你要销毁的物品名称或编号: ");
        int number = Integer.parseInt(JOptionPane.showInputDialog("请输入你要销毁的数量: "));
        Boolean flag = true;
        for (Grids g : gridsList) {
            if (g.getGoods() != null&&!g.getGoods().getType().equals("道具") && g.getGoods().getName().equals(nameOrId) || g.getGoods().getId().equals(nameOrId)) {
                flag = false;
                if (g.getNumber() > number) {
                    g.setNumber(g.getNumber() - number);
                    JOptionPane.showMessageDialog(null, "销毁成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }else{
                    g.setGoods(null);
                    JOptionPane.showMessageDialog(null, "销毁成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            if (flag) {
                JOptionPane.showMessageDialog(null, "没有找到该物品或该物品不可销毁！", "提示", JOptionPane.ERROR_MESSAGE);
                break;
            }
        }
    }
    //交易指定物品
    public void tradeGoods() {
        String nameOrId1 = JOptionPane.showInputDialog("请输入你要交易的物品名称或编号: ");
        int number=Integer.parseInt(JOptionPane.showInputDialog("请输入你要交易的数量: "));
        boolean flag=true;
        for(Grids g:gridsList) {
            if (g.getGoods() != null&&!g.getGoods().getType().equals("道具") && g.getGoods().getName().equals(nameOrId1) || g.getGoods().getId().equals(nameOrId1)) {
                flag = false;
                System.out.println(g.getGoods().getIdentify());
                if(g.getGoods().getIdentify()&&g.getNumber()>=number){
                    wallet.setBalance((int) (wallet.getBalance()+g.getGoods().getPrice()*0.9));
                    JOptionPane.showMessageDialog(null,"交易成功！","提示",JOptionPane.INFORMATION_MESSAGE);
                    break;
                }else{
                    JOptionPane.showMessageDialog(null,"该物品未鉴定或者数量不够！","提示",JOptionPane.ERROR_MESSAGE);
                    break;
                }
            }
        }
        if(flag){
            JOptionPane.showMessageDialog(null,"没有找到该物品！","提示",JOptionPane.ERROR_MESSAGE);
        }
    }
    //背包整理
    public void bagClean() {
        int count=0;
        for(int i=0;i<gridsList.size();i++){
            if(gridsList.get(i).getGoods()!=null){
                if(gridsList.get(i).getGoods().getType().equals("消耗品")){
                    Collections.swap(gridsList,i,count);
                    count++;
                }
            }
        }
        for(int i=0;i<gridsList.size();i++){
            if(gridsList.get(i).getGoods()!=null){
                if(gridsList.get(i).getGoods().getType().equals("材料")){
                    Collections.swap(gridsList,i,count);
                    count++;
                }
            }
        }
        for(int i=0;i<gridsList.size();i++){
            if(gridsList.get(i).getGoods()!=null){
                if(gridsList.get(i).getGoods().getType().equals("装备")){
                    Collections.swap(gridsList,i,count);
                    count++;
                }
            }
        }
        for(int i=0;i<gridsList.size();i++){
            if(gridsList.get(i).getGoods()!=null){
                if(gridsList.get(i).getGoods().getType().equals("道具")){
                    Collections.swap(gridsList,i,count);
                    count++;
                }
            }
        }
        JOptionPane.showMessageDialog(null,"整理完成！");
    }
    //背包扩容
    public void expandBag(){
        for(int i=0;i<10;i++){
            gridsList.add(new Grids());
        }
        JOptionPane.showMessageDialog(null,"扩容成功！");
    }
    //拿到物品
    public Goods getGoodsById(String nameOrId){
        String GoodsById=GoodsList.getGoodsInfo(nameOrId);
        if(GoodsById==null){
            return null;
        }
        String[] params=GoodsById.split(" ");

        String name=params[0];
        String id=params[1];
        String type=params[2];
        int price=Integer.parseInt(params[3]);
        String description=params[4];
        switch (type){
            case "消耗品":
                return new Consumables(name,id,type,price,description);
            case "材料":
                return new Material(name,id,type,price,description);
            case "装备":
                int rank=Integer.parseInt(params[5]);
                int quality=Integer.parseInt(params[6]);
                return new Equipments(name,id,type,price,description,rank,quality);
            case "道具":
                String property=params[5];
                return new Props(name,id,type,price,description,property);
        }
        return null;
    }
}
