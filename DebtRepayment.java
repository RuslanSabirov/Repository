/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debtrepayment;


import java.util.*;
import java.text.*;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class DebtRepayment {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        String file = "";
        
        int countMount = 0;
        double amount = 0, interestRate = 0;
        
        try{
            System.out.println("колличество месяцев = ");
            countMount = sc.nextInt();
            System.out.println("сумма = ");
            amount = sc.nextDouble();
            System.out.println("ставка = ");
            interestRate = sc.nextDouble();
            
            System.out.println("путь к файлу : ");
            file = sc.nextLine();
        }catch(Exception ex){
            System.out.print("Ошибка ввода!");
            return;
        }
        
        AnnuityPayment annuityPayment = new AnnuityPayment(countMount, amount, interestRate);
        ArrayList<Shedule> shedule = annuityPayment.getShedule();
        
        HSSFWorkbook book = new HSSFWorkbook();
        HSSFSheet sheet = book.createSheet("Employees sheet");
        
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Дата");
        row.createCell(1).setCellValue("Основная");
        row.createCell(2).setCellValue("Добавочная");
              
        for(int i = 0; i < shedule.size(); i++){
            row = sheet.createRow(i + 1);
            
            row.createCell(0).setCellValue(shedule.get(i).getDate().toString());
            row.createCell(1).setCellValue(shedule.get(i).getMainDebt());
            row.createCell(2).setCellValue(shedule.get(i).getSurplusDebt());
        }
          
        try{
            book.write(new FileOutputStream(file));
            book.close();
        }
        catch(FileNotFoundException ex){
            System.out.print("Ошибка записи файла!");         
        }
        catch(IOException ex){
            System.out.print("Ошибка доступа!!");
        }
    }
}


class AnnuityPayment{
    private int countMount;
    private double amount;
    private double interestRate;
    
    private Date beginDate;
    
    public AnnuityPayment(int countMount, double amount, double interestRate){
        this.countMount = countMount;
        this.amount = amount;
        this.interestRate = interestRate;
        
        beginDate = new Date();
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
            calendar.add(Calendar.MONTH, 1);
        }
        
        return shedule;
    }
}


class Shedule{
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
}
