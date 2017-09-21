package ru.job4j.array;

import java.util.Arrays;

/**
 * ArrayDuplecate
 *
 * @author Vitaly Zubov (mailto:Zubov.VP@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
 public class CheckDuplicate{
	 /**
     * Main.
     * @param String - boolean.
     */
	public boolean contains(String origin, String sub){
		boolean check = false;
        char[] arrayOrigin = origin.toCharArray();
        char [] arraySub = sub.toCharArray();
        for(int outsaid = 0; outsaid<arraySub.length; outsaid++){
            for(int inside=0; inside<arrayOrigin.length; inside++){
               check = arraySub[outsaid] == arrayOrigin[inside] ? true:false;
               if(check == true){
                  break;
               }

               }
            if (check == false) {
                break;
            }
            }
	}
 }