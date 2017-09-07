package ru.job4j.array;

public class BubbleSort{
	public int[] sort(int[] array){
		//Данный цикл отвечает за то, сколько раз нужно крутить метод для того чтобы 100% достигнуть результата
		for(int x=0; x<array.length); x++){
			//Данный цикл переберает каждое число в массиве
			for(int y=0; x<array.length-1;x++){
				//Данное условие производить замену переменных в массиве
				//Условие x<array.length-1 используем для того чтобы в дальнейшем не выйти за пределы массива(когда будем менять значения)
				if(array[x]>=array[x+1]){
					int q = array[x];
					int [w] = array[x+1];
					array[x] = w;
					array [x+1] = q;
				}
			}
		}
	}
	//Возращаем массив
	return array [];
}
		
		
		