package com.company;

import java.util.Scanner;

public class GetFile {



    public String getFilePath()
    {
        System.out.println("Please input file directory!");
        Scanner scan = new Scanner(System.in);

        String path=scan.nextLine();

        return path;
    }
}
