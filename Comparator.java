/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparator;

import java.util.Arrays;

/**
 *
 * @author Руслан
 */
public class Comparator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Person[] persons = new Person[]{new Person("Ivan", 15), new Person("Tom", 27), new Person("Alex", 15)};
        
        Arrays.sort(persons);
        
        System.out.println(Arrays.toString(persons));
    }
    
}

class Person implements Comparable<Person>
{
    private String name;
    private int age;
        
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }
        
    public String getName(){
        return name;
    }
        
    public int getAge(){
        return age;
    }
    
    public int compareTo(Person person){
        if(age > person.getAge()){
            return 1;
        }
        if(age < person.getAge()){
            return -1;
        }
            
        return name.compareTo(person.getName());
    }
        
    public String toString(){
        return name + " " + age;
    }        
}