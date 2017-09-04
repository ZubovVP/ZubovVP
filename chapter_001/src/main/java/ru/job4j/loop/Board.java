package ru.job4j.loop;

public class Board {
	StringBuilder paint = new StringBuilder();
	public String paint(int width, int height) {
		 for (int x = 1; x<=height; x++){
			 for (int y = 1; y<=width; y++){
				 if ((x+y)%2==0){
					 paint.append("x");
				 } else {
					 paint.append(" ");
				}
			}
		 paint.append(System.getProperty("line.separator"));
		}
	}
}