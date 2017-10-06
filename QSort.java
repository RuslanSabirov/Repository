/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qsort;

import java.util.Arrays;
import java.util.Date;

/**
 *
 * @author Руслан
 */
public class QSort {

    /**
     * @param args the command line arguments
     */
   public static void main(String[] args) {
        Date time;
        double oneTime, twoTime;

        int[] masOne = new int[] { 5, 2, 3, 1, 7, 9, 4 };  
        
        System.out.println(Arrays.toString(masOne));
        
        time = new Date();
        Arrays.sort(masOne);
        oneTime = ((new Date()).getTime() - time.getTime());          
        
        time = new Date();
        reversQSort(masOne);
        twoTime = ((new Date()).getTime() - time.getTime());
               
        System.out.println("time Arrays.sort: " + oneTime);
        System.out.println("time reversQSort: " + twoTime);
        
        System.out.println(Arrays.toString(masOne));    
    }   

    public static void reversQSort(int[] mas)
    {
        reverSort(mas, 0, mas.length - 1);
    }

    static void reverSort(int[] mas, int start, int end)
    {
        if (start >= end) return;
        int mark = partition(mas, start, end);

        reverSort(mas, start, mark - 1);
        reverSort(mas, mark + 1, end);

    }

    static int partition(int[] mas, int start, int end)
    {
        int mark = start;
        for (int i = start; i <= end; i++)
            if (mas[i] >= mas[end])
            {
                int temp = mas[i];
                mas[i] = mas[mark];
                mas[mark] = temp;
                mark++;
            }
        return mark - 1;
    }
    
}
