package ru.job4j;

import java.lang.*;

public class Max {
	public int max(int first , int second) {
          return first > second ? first : second;
      }
	public int max(int first, int second, int third) {
		int max = first > second ? first : second;
		return max > third ? max : third;
	}
}