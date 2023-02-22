package com.swift.optimization;

/**
 * 数字运算比字符串操作快
 * @author Lynch
 * @since
 */
public class RanDomCode {
  public static void main(String[] args) {
    int sum = 10000;
    long start = System.currentTimeMillis();
    for (int i = 0; i < sum; i++) {
      String code = (Math.random()+"").substring(2,8);
    }
    long end = System.currentTimeMillis();
    System.out.println("时间耗费："+(end-start));

    long start1 = System.currentTimeMillis();
    for (int i = 0; i < sum; i++) {
      String code = String.valueOf((Math.random()*9+1)*Math.pow(10,5));
    }
    long end1 = System.currentTimeMillis();
    System.out.println("时间耗费："+(end1-start1));

  }

}
