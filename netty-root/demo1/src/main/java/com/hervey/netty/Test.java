package com.hervey.netty;

import java.util.Scanner;

/**
 * @program: netty-root
 * @description: ceshi
 * @author: hewen
 * @create: 2019-08-22 14:27
 **/
public class Test {

    public static void main(String[] args) {
        String a = "1" + "2";

        String b = "12";

        String c = b;


        System.out.println(a == b);

        System.out.println(a.equals(b));

        System.out.println(c == b);

        System.out.println(c.equals(b));


        Integer a1 = 2000;
        Integer b1 = 2000;

        System.out.println(a1 == b1);
        System.out.println(a1.equals(b1));

        Scanner sc = new Scanner(System.in);
        //利用hasNextXXX()判断是否还有下一输入项
        while (sc.hasNext()) {
            //利用nextXXX()方法输出内容
            String str = sc.next();
            System.out.println(str);
        }

    }
}
