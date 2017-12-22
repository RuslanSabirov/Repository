/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package debtrepaymentju;

import java.util.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Руслан
 */
public class AnnuityPaymentTest {
    AnnuityPayment instance;
    Date nowDate;
   
    @Before
    public void setUp() {
       nowDate = new Date();
       instance = new AnnuityPayment(12, 100000, 120, nowDate);
    }
    
    @After
    public void tearDown() {
       instance = null;
    }

    /**
     * Test of getShedule method, of class AnnuityPayment.
     */
    @Test
    public void testGetShedule() {
        System.out.println("getShedule");
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        
        Date[] dates = new Date[12];
        for(int i = 0; i < 12; i++){
            dates[i] =  calendar.getTime();
            calendar.add(Calendar.MONTH, 1);
        }
        
        ArrayList<Shedule> expResult = new ArrayList<Shedule>(12);
        expResult.add( new Shedule(dates[0], 4676.331510028724, 10000.0));
        expResult.add( new Shedule(dates[1], 5143.964661031596, 9532.366848997128));
        expResult.add( new Shedule(dates[2], 5658.361127134756, 9017.970382893967));
        expResult.add( new Shedule(dates[3], 6224.197239848232, 8452.134270180492));
        expResult.add( new Shedule(dates[4], 6846.616963833056, 7829.714546195668));
        expResult.add( new Shedule(dates[5], 7531.278660216362, 7145.052849812361));
        expResult.add( new Shedule(dates[6], 8284.406526237999, 6391.9249837907255));
        expResult.add( new Shedule(dates[7], 9112.847178861797, 5563.484331166926));
        expResult.add( new Shedule(dates[8], 10024.131896747978, 4652.199613280746));
        expResult.add( new Shedule(dates[9], 11026.545086422775, 3649.786423605948));
        expResult.add( new Shedule(dates[10], 12129.199595065053, 2547.1319149636706));
        expResult.add( new Shedule(dates[11], 13342.119554571558, 1334.2119554571655));

        ArrayList<Shedule> result = instance.getShedule();
        assertTrue(Arrays.equals(expResult.toArray(), result.toArray()));
    
    }
    
}
