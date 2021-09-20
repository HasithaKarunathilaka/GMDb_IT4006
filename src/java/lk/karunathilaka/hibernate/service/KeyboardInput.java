/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.karunathilaka.hibernate.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Hasitha
 */
public class KeyboardInput {
    
    public String inputString(){
        Scanner keyboardInput= new Scanner(System.in);
        String value = keyboardInput.nextLine();
//        keyboardInput.close();
        return value;
        
    }
    
    public Date inputDate(){
        Date date = null;
    
        Scanner keyboardInput= new Scanner(System.in);
        String value = keyboardInput.nextLine();
        
        SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
        
        try {
            date = sd.parse(value);
        } catch (ParseException ex) {
//            System.out.println(ex.toString());
        }
    
        return date;
    }
    
    public int inputNumber(){
        Scanner keyboardInput= new Scanner(System.in);
        int value = keyboardInput.nextInt();
//        keyboardInput.close();
        return value;
        
    }
    
}
