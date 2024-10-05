package JavaLearn.day02;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Read {
    public static void main(String[] args) throws FileNotFoundException {
        BufferedReader br=new BufferedReader(new FileReader("D:\\myRecord.txt"));
        try {
            System.out.println(br.readLine());
            String line="";
            line=br.readLine();
            String[] xyd=line.split(" ");
            System.out.println(Integer.parseInt(xyd[0])+xyd[1]+xyd[2]);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
