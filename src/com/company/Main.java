package com.company;

import jdk.jshell.spi.ExecutionControl;

public class Main {

    public static void main(String[] args) throws ExecutionControl.NotImplementedException {
	// write your code here
        String input = "0110\n" +
            "0001 0010 0010 0011\n" +
            "0010 0001 0100\n" +
            "0011 0010 0101 0110\n" +
            "0100 0001 0110\n" +
            "0101 0000\n" +
            "0110 0000\n" +
            "\n" +
            "t(1x4)eks(2x2)t\n" +
            "t(1x3)e(1x2)ks(3x3)t(1x2)\n" +
            "przy(2x6)kl(1x10)ad\n" +
            "ksia(1x2)zka\n" +
            "rez(3x5)erw(2x10)ac(3x3)ja(1x2)\n" +
            "dow(2x2)od(1x2)\n";
        Reader reader = new ReaderImpl();
        reader.read(input);
    }
}
