/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debtrepaymentju;

/**
 *
 * @author Руслан
 */
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

public class DebtRepaymentJU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
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
        
        AnnuityPayment annuityPayment = new AnnuityPayment(countMount, amount, interestRate, new Date());
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
