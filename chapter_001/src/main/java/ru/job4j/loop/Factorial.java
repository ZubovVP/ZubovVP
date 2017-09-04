package ru.job4j.loop;

public class Factorial {
	public int calc(int n){
		int factorial = 0;
		if (this.exist(n)){
			if(n>0){
				for (int x = 1; x<=n; x++){
					factorial *=x;
				} else {
					factorial = 1;
				}
				return factorial;
				
			}
			
		}
		
	}
	 private boolean exist(int n){
	   return this.n > 0;
	 }
}