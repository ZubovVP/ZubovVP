package ru.job4j.convert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru).
 * @version $Id$
 * @since 05.02.2018
 */
 public class Convert {

     /**
      * Convert an array into a collection
      *
      * @param array - the array
      * @return List - the collection.
      */
    public List<Integer> toList(int[][] array) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int[] number : array) {
            for (int  numberOne : number) {
                list.add(numberOne);
            }
        }
        return list;
    }

     /**
      * Convert a collection into an array
      *
      * @param list - the collection
      * @param rows - rows
      * @return int [][] - the array.
      */
     public int[][] toArray(List<Integer> list, int rows) {
        int positinon = 0;
        int position2 = 0;
        int x = (int) Math.ceil((double) list.size() / rows);
        int[][] array = new int[x][rows];
            for (int first : list) {
                array[positinon][position2] = first;
                position2++;
                if (position2 == rows) {
                    positinon++;
                    position2 = 0;
                }
            }
            return array;
        }

     /**
      * Convert
      *
      * @param list - the collection
      * @return List<Integer> - the collection.
      */
      List<Integer> convert(List<int[]> list) {
         ArrayList<Integer> result = new ArrayList<>();
          for (int[] array : list) {
              for (int number : array) {
                  result.add(number);
              }
          }
          return result;
     }
}