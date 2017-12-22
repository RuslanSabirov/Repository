/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debtrepaymentju;
import java.util.*;
/**
 *
 * @author Руслан
 */
public class Shedule
{
    private Date date;
    private double mainDebt;
    private double surplusDebt;
        
    public Shedule(Date date, double mainDebt, double surplusDebt){
        this.date = date;
        this.mainDebt = mainDebt;
        this.surplusDebt = surplusDebt;
    }
    
    public Date getDate(){
        return date;
    }
    public double getMainDebt(){
        return mainDebt;
    }
    public double getSurplusDebt(){
        return surplusDebt;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Shedule shedule = (Shedule)o;
        
        return (shedule.getDate().equals(this.date) && shedule.getMainDebt() == this.mainDebt && shedule.getSurplusDebt() == this.surplusDebt);
    }
}
