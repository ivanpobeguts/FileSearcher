/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filesearcher;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Главный класс программы
 *
 * @author IvanP_000
 */
public class FileSearcher {

    public static void main(String[] args) {
        String file;
        Searcher fs = new Searcher();
        Scanner input = new Scanner(System.in);
        System.out.println("If you know regex, you can use them, if not - you can "
                + "enter a part of the name of file.");
        System.out.println("If you want to know size of a file and when it was modified, "
                + "please enter 'SPACE' and '#' right after your request.");
        System.out.println("Thank you!");
        System.out.println("Enter request:");
        file = input.nextLine();
        System.out.println();
        System.out.println("Your results:");    
        if (file.endsWith("#")) {
            file = file.substring(0, file.indexOf("#") - 1);
            System.out.printf("%-35s %-85s %-28s %7s%n", "File/Dir", "Pass", "LastModified", "Size");
            fs.p = Pattern.compile(file);
            fs.search(file, fs.dir, true);
        } else {
            System.out.printf("%-30s %10s%n", "File/Dir", "Pass");
            fs.p = Pattern.compile(file);
            fs.search(file, fs.dir, false);
        }
    }
}
