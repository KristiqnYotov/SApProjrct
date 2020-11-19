package com.company;

import java.io.*;
import java.util.Scanner;


public class Main  {

    public static void main(String[] args)  {
        GetFile f1 = new GetFile();
        String wordsArr[][] = new String[50][50];
        int wordCounter=0;
        int rowCounter =0;
        char caster;
        String currentWord="";
        String filePath = f1.getFilePath();

        //Read words from File
        try {
            FileReader reader = new FileReader(filePath);
            int data = reader.read();
            while (data != -1) {
                caster = (char) data;
                if (caster == '\n' || caster == '\r') {
                    wordsArr[rowCounter][wordCounter] = currentWord;
                    wordCounter = 0;
                    rowCounter++;
                    currentWord = "";
                } else {
                    if (caster == ' ') {
                        wordsArr[rowCounter][wordCounter] = currentWord;
                        wordCounter++;
                        currentWord = "";
                    } else {
                        currentWord += caster;
                    }
                }
                data = reader.read();
            }
            wordsArr[rowCounter][wordCounter] = currentWord;
            rowCounter++;
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Choose command");
        System.out.println("1) swap row");
        System.out.println("2) swap two words");
        System.out.println("3) exit");
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        while(!command.equalsIgnoreCase("exit"))
        {
            if(command.equalsIgnoreCase("swap row"))
            {
                Scanner scanIndex = new Scanner(System.in);
                System.out.println("Input the first row you want to swap!");
                int firstSwapRow= scan.nextInt();
                System.out.println("Input the second row you want to swap!");
                int secondSwapRow = scan.nextInt();
                swapRowFunction(firstSwapRow, secondSwapRow, wordsArr);
            }
            if(command.equalsIgnoreCase("swap two words"))
            {
                Scanner scanIndex = new Scanner(System.in);
                System.out.println("Input the row of the first word you want to swap");
                int fisrtWordRow = scan.nextInt();
                System.out.println("Input the index of the first word you want to swap");
                int firstWordIndex = scan.nextInt();
                System.out.println("Input the row of the second word you want to swap");
                int secondWordRow = scan.nextInt();
                System.out.println("Input the index of the second word you want to swap");
                int secondWordIndex = scan.nextInt();

                swapWordFunction(fisrtWordRow,firstWordIndex,secondWordRow,secondWordIndex, wordsArr);

            }
            System.out.println("Choose command");
            System.out.println("1) swap row");
            System.out.println("2) swap two words");
            System.out.println("3) exit");
            command = scan.nextLine();
        }

        saveFile(wordsArr,rowCounter,filePath);


    }
    public static void swapRowFunction(int index1, int index2, String[][] swap)
    {

        String tempArray[][] = new String[50][50];
        tempArray = swap.clone();
        swap[index1] = tempArray[index2];
        swap[index2] = tempArray[index1];

    }
    public static void swapWordFunction(int index1, int index2, int index3, int index4, String[][] swap)
    {
        String firstWord = swap[index1][index2];
        String secondWord = swap[index3][index4];
        swap[index3][index4] = firstWord;
        swap[index1][index2] = secondWord;
    }
    public static int[] notNullCells(String[][] arr, int rows)
    {
        int array[] = new int[50];
        for(int i=0; i<rows; i++)
        {
            for (int j = 0; j < 50; j++) {
                if(arr[i][j]!=null)
                {
                    array[i]++;
                }
            }
        }
        return array;
    }

    public static void saveFile(String[][] wordsArr, int rowCounter, String filePath)
    {
        int wordsOnRows[]= notNullCells(wordsArr, rowCounter);
        try {
            FileWriter writer = new FileWriter(filePath);
            for(int i=0; i< rowCounter; i++)
            {
                for(int p = 0; p < wordsOnRows[i];  p++)
                {
                    writer.write(wordsArr[i][p]);
                    writer.write(" ");
                }
                writer.write('\n');
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
