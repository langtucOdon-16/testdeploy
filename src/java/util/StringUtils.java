/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 * 
 * @author NamTQ
 * Date: 20/02/2026
 * Description: 
 * Utility class that provides helper methods for String-related operations.
 * This class contains common reusable methods for processing and validating String values.
 * 
 */
public class StringUtils {
    
    /**
     * Checks whether the given target string exists in the provided array.
     *
     * This method performs a linear search through the array and compares each element using the equals() method.
     *
     * @param arr     the array of strings to search in
     * @param target  the string value to search for
     * @return true if the target string exists in the array, false otherwise
     *
     * @throws NullPointerException if arr or target is null
     */
    public static boolean contains (String[] arr, String target){
        if (arr == null || target == null) {
            return false;
        }
        
        for (String str : arr) {
            if (str.equals(target)){
                return true;
            }
        }
        return false;
    }
    
}
