package com.study.effectiveJava;

import java.util.Comparator;

public class Item14 {

  public static void main() {
    Comparator<Integer> comparator = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return 0;
      }
    };
//    comparator
//        .thenComparingInt()
//        .thenComparingInt();
  }

}
