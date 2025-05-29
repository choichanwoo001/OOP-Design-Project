package Delivery_simlulator;

import java.util.*;

// 정돈 - 돈까스
public class Restaurant1 extends Restaurant{
    List<MenuItem> menuItems = new ArrayList<>();

    void restaurantInit(){
        menuItems.add(new MenuItem("로스카츠", 10000));
        menuItems.add(new MenuItem("히레카츠", 11000));
        menuItems.add(new MenuItem("치즈카츠", 12000));
        menuItems.add(new MenuItem("카레카츠", 13000));
        menu = menuItems;
    }

    Restaurant1(String name, String address) {
        super(name, address);
        this.restaurantInit();
    }

    void showMenu(){
        for(MenuItem items : menuItems){
            System.out.println(items.name);
        }
    }
}

// 시골야채된장
class Restaurant2 extends Restaurant{
    List<MenuItem> menuItems = new ArrayList<>();

    void restaurantInit(){
        menuItems.add(new MenuItem("시골야채된장비빔밥", 10000));
        menuItems.add(new MenuItem("목살김치된장", 11000));
        menuItems.add(new MenuItem("청국장", 12000));
        menuItems.add(new MenuItem("생삼겹살", 13000));
        menu = menuItems;
    }

    Restaurant2(String name, String address) {
        super(name, address);
        this.restaurantInit();
    }

    void showMenu(){
        for(MenuItem items : menuItems){
            System.out.println(items.name);
        }
    }
}

// 딘타이펑 - 딤섬 전문
class Restaurant3 extends Restaurant{
    List<MenuItem> menuItems = new ArrayList<>();

    void restaurantInit(){
        menuItems.add(new MenuItem("샤오롱바오", 10000));
        menuItems.add(new MenuItem("새우샤오마이", 11000));
        menuItems.add(new MenuItem("계란볶음밥", 12000));
        menuItems.add(new MenuItem("소고기탕면", 13000));
        menu = menuItems;
    }

    Restaurant3(String name, String address) {
        super(name, address);
        this.restaurantInit();
    }

    void showMenu(){
        for(MenuItem items : menuItems){
            System.out.println(items.name);
        }
    }
}

// 고에몬 - 우동 전문
class Restaurant4 extends Restaurant{
    List<MenuItem> menuItems = new ArrayList<>();

    void restaurantInit(){
        menuItems.add(new MenuItem("수플레오믈렛 멘타이꼬 리조또", 10000));
        menuItems.add(new MenuItem("수플레오믈렛 새우크림리조또", 11000));
        menuItems.add(new MenuItem("까르보나라 멘타이꼬", 12000));
        menuItems.add(new MenuItem("멘타이꼬 슈림프 파스타", 13000));
        menu = menuItems;
    }

    Restaurant4(String name, String address) {
        super(name, address);
        this.restaurantInit();
    }

    void showMenu(){
        for(MenuItem items : menuItems){
            System.out.println(items.name);
        }
    }
}

