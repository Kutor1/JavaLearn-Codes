package JavaLearn.day01;

import java.awt.print.Book;
import java.nio.Buffer;
import java.util.*;

public class test {
    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        list.add("123");
        list.add("23");
        list.add("3");
        Collections.sort(list,new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String)o1).length()-((String)o2).length();
            }
        });
        System.out.println(list);

    }
}

