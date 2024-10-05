package JavaLearn.day02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FilterWriter;
import java.io.PrintStream;

public class BufferedWriter_ {
    public static void main(String[] args) throws Exception{
        String str="D:\\a.txt";
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str,true));
        bufferedWriter.write("hello world");
        bufferedWriter.close();

    }

}
