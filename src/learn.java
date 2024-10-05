import java.util.ArrayList;
import java.util.Scanner;

public class learn {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 读取初始链表数据
        int n = scanner.nextInt();
        ArrayList<Integer> list = new ArrayList<>();

        // 倒序读取数据，插入到 list 中
        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            list.add(0, value); // 插入到列表开头，保持逆序
        }

        // 读取操作数
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            String operation = scanner.next();

            if (operation.equals("show")) {
                // 显示当前列表
                if (list.isEmpty()) {
                    System.out.println("Link list is empty");
                } else {
                    for (int j = 0; j < list.size(); j++) {
                        System.out.print(list.get(j) + " ");
                    }
                    System.out.println();
                }
            } else if (operation.equals("get")) {
                // 获取指定位置元素
                int index = scanner.nextInt();
                if (index >= 1 && index <= list.size()) {
                    System.out.println(list.get(index - 1));
                } else {
                    System.out.println("get fail");
                }
            } else if (operation.equals("insert")) {
                // 在指定位置插入元素
                int index = scanner.nextInt();
                int value = scanner.nextInt();
                if (index >= 1 && index <= list.size() + 1) {
                    list.add(index - 1, value);
                    System.out.println("insert OK");
                } else {
                    System.out.println("insert fail");
                }
            } else if (operation.equals("delete")) {
                // 删除指定位置的元素
                int index = scanner.nextInt();
                if (index >= 1 && index <= list.size()) {
                    list.remove(index - 1);
                    System.out.println("delete OK");
                } else {
                    System.out.println("delete fail");
                }
            }
        }

        scanner.close();
    }
}


