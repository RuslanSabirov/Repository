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
public class AnnuityPayment{
    private int countMount;
    private double amount;
    private double interestRate;
    
    private Date beginDate;
    
    public AnnuityPayment(int countMount, double amount, double interestRate, Date nowDate){
        this.countMount = countMount;
        this.amount = amount;
        this.interestRate = interestRate;
        
        beginDate = nowDate;
    }
    
    public int getCountMount(){
        return countMount;
    }
    public double getAmont(){
        return amount;
    }
    public double getInterestRate(){
        return interestRate;
    }
    
    public ArrayList<Shedule> getShedule() 
    {
        ArrayList<Shedule> shedule = new ArrayList<Shedule>(countMount);
        double annuityPayment = (amount * interestRate / 1200) /
                               (1 - Math.pow(1 + interestRate / 1200, -countMount));
        
        double remainingAmount = amount;
        
        Calendar calendar = Calendar.getInstance();
        
        calendar.setTime(beginDate);
        
        for(int i = 0; i < countMount; i++){
            double surplusDebt = remainingAmount * interestRate / 1200;
            double mainDebt = annuityPayment - surplusDebt;
            remainingAmount = remainingAmount - mainDebt;
            
            shedule.add(new Shedule(calendar.getTime(), mainDebt, surplusDebt));
            System.out.println(mainDebt);
            System.out.println(surplusDebt);
            
            calendar.add(Calendar.MONTH, 1);
        }
        
        return shedule;
    }
}
