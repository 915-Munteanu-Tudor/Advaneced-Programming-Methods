package com.company;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        float average, sum = 0;
        int number, cnt = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of numbers:");
        int i = sc.nextInt();

        while (cnt <= i){
            System.out.println("Enter an integer:");
            number = sc.nextInt();
            sum = sum + number;
            cnt++;
        }

        average = sum/i;
        System.out.println("The average is:" + average);
    }
}
