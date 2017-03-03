package com.lxy.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gaohuang on 17-2-27.
 */

// http://blog.csdn.net/thisinnocence/article/details/20997191
//http://www.geekboy.org/huaweioj-three/

public class Test {

    //字符串反转
    public static String reverse1(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    //辗转相除法:最大公约数
    public static int commonDivisor(int m, int n) {
        //n > m
        if (n < m) {
            int temp = n;
            n = m;
            m = temp;
        }

        while (n % m != 0) {
            int temp = n % m;
            n = m;
            m = temp;
        }
        return m;
    }

    //最小公倍数
    public static int commonMultiple(int n, int m) {
        if (n == 0) {
            return 0;
        }

        if (m == 0) {
            return 0;
        }

        return n * m / commonDivisor(n, m);
    }

    //字符种类计数
    public static int characterTypesCount(String s) {
        s = s.trim();

        if (s.equals("")) {
            return 0;
        }

        char[] characterArr = s.toCharArray();

        Set<Character> set = new HashSet<>();

        for (int i : characterArr) {
            if (!Character.isDigit(characterArr[i]) && !Character.isLetter(characterArr[i])) {
                continue;
            }

            if (!set.contains(characterArr[i])) {
                set.add(characterArr[i]);
            }
        }
        return set.size();
    }

    // 总成绩
    public static int sumScore(ArrayList<Integer> scoreList) {
        int sumScore = 0;
        for (int score : scoreList) {
            if (score >= 0 && score <= 150) {
                sumScore += score;
            }
        }
        return sumScore;
    }

    // 平均成绩
    public static int averageScore(ArrayList<Integer> scoreList) {
        return sumScore(scoreList) / 4;
    }

    public static List<Integer> stringMatch(List<String> stringList, String matchStr) {
        List<Integer> numberList = new ArrayList<>();

        for (int i = 0, len = stringList.size(); i < len; i++) {
            if (stringList.get(i).contains(matchStr)) {
                numberList.add(Integer.valueOf(stringList.get(i)));
            }
        }

        Collections.sort(numberList);

        return numberList;
    }

    public static void printStringMatch(List<String> stringList) {
        List<Integer> numberList = stringMatch(stringList, "123");
        for (int i = 0, len = numberList.size(); i < len; i++) {
            if (i == len - 1) {
                System.out.print(numberList.get(i));
            } else {
                System.out.print(numberList.get(i) + ",");
            }
        }
        System.out.println();
    }

    /*
    *
    二进制——>十进制
        1101（2）=1*2^0+0*2^1+1*2^2+1*2^3=1+0+4+8=13
    八进制——>十进制
        1101（8）=1*8^0+0*8^1+1*8^2+1*8^3=1+0+64+512=577
        425(8) = 5*8^0+2*8^1+4*8^2 = 5+16+256=277
    十六进制——>十进制
        1101（16）=1*16^0+0*16^1+1*16^2+1*16^3=1+256+4096=4353
        AF(16) = 15*16^0 + 10*16^1 = 15+160=175
    二进制——>八进制
        (1100100)2=(001 100 100)2=(1 4 4)8=(9)2
    二进制——>十六进制
        1000 1001 1010 1011 1100 1101 1110 1111 =(89ABCDEF)16
    */

    //进制转换
    public static void test() {
        System.out.println(Integer.parseInt("1101", 2));
        System.out.println(Integer.parseInt("ff", 16));
        System.out.println(Integer.parseInt("ff", 16));
        System.out.println(Integer.parseInt("07", 8));

        System.out.println(Integer.toBinaryString(16));
        System.out.println(Integer.toOctalString(16));
        System.out.println(Integer.toHexString(16));
    }


    public static void countAll(int i, String str, int[] num) {
        if (i == num.length) {
            System.out.println(str);
            return;
        }
        countAll(i + 1, str, num);
        countAll(i + 1, str + num[i] + ",", num);
    }

    public static void count(int i, String str, int[] num, int n) {
        if (n == 0) {
            System.out.println(str);
            return;
        }
        if (i == num.length) {
            return;
        }
        count(i + 1, str + num[i] + ",", num, n - 1);
        count(i + 1, str, num, n);
    }

    // apple 个 苹果 basket 个 篮子
    private static int ShareApple(int apple, int basket) {
        // 因为我们总是让apple >= basket来求解的，所以apple - basket >= 0,
        // 让apple = 0时候结束，如果改为apple = 1，可能得不到正确解
        if (apple == 0 || basket == 1) {
            return 1;
        }//if
        // 篮子多于苹果 按照苹果个数分
        else if (apple < basket) {
            return ShareApple(apple, apple);
        }//else
        return ShareApple(apple, basket - 1) + ShareApple(apple - basket, basket);
    }

    private static String whichWeek(String date) {
        Date data = new Date(date);
        SimpleDateFormat dateFormat = new SimpleDateFormat();
        String week = dateFormat.format(data);
        System.out.println();
        return week;
    }

    private static boolean isZhiShu(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /*
    合法E-mail地址：
    1. 必须包含一个并且只有一个符号“@”
    2. 第一个字符不得是“@”或者“.”
    3. 不允许出现“@.”或者.@
    4. 结尾不得是字符“@”或者“.”
    5. 允许“@”前的字符中出现“＋”
    6. 不允许“＋”在最前面，或者“＋@”
    */
    private static boolean isEmail(String s) {
        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern regex = Pattern.compile(check);
        Matcher matcher = regex.matcher(s);
        boolean isMatched = matcher.matches();
        System.out.println(isMatched);
        return isMatched;
    }

    /*
    字符描述：

    ^ ：匹配输入的开始位置。
    \：将下一个字符标记为特殊字符或字面值。
    * ：匹配前一个字符零次或几次。
    + ：匹配前一个字符一次或多次。
    (pattern) 与模式匹配并记住匹配。
    x|y：匹配 x 或 y。
    [a-z] ：表示某个范围内的字符。与指定区间内的任何字符匹配。
    \w ：与任何单词字符匹配，包括下划线。
    $ ：匹配输入的结尾。
    * */

    /* 电话号码

    String check = "^(13[4,5,6,7,8,9]|15[0,8,9,1,7]|188|187)\\d{8}$";
     Pattern regex = Pattern.compile(check);
     Matcher matcher = regex.matcher("13555655606");
     boolean isMatched = matcher.matches();
     System.out.println(isMatched);

    */


    public static int a[] = new int[10];  //用来存放输入的数值
    public static int b[] = new int[10];  //用来存放该值出现的次数

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        // 字符串反转
//        String s = "abcde 111";
//        System.out.println("字符串反转:" + reverse1(s));

        //最大公约数
//        System.out.println(commonDivisor(2, 2));

        //最小公倍数
//        System.out.println(commonMultiple(6, 6));

        //字符种类计数
//        System.out.println(characterTypesCount(" "));

        // 平均成绩,总成绩
//        ArrayList<Integer> scoreList = new ArrayList<>();
//        Scanner scanner = new Scanner(System.in);
//        String line = "";
//        while (scanner.hasNext()) {
//            line = scanner.nextLine();
//            String[] lineArr = line.split(" ");
//
//            for (String s : lineArr) {
//                scoreList.add(Integer.valueOf(s));
//            }
//
//            System.out.println("总成绩=" + sumScore(scoreList) + ";平均成绩=" + averageScore(scoreList));
//        }


        // 取出整型数据中存在指定整数的数据(字串匹配)，并按照升序排列返回
//        List<String> stringList = new ArrayList<>();
//
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            String s = scanner.nextLine();
//            String[] ss = s.split(" ");
//            String[] numberArr = ss[0].split(",");
//            System.out.println("numberArr.len=" + numberArr.length + ",ss[1]=" + ss[1]);
//
//            for (String numberStr:numberArr){
//                stringList.add(numberStr);
//            }
//
//            System.out.println("stringList.size=" + stringList.size());
//
//            printStringMatch(stringList);
//
//        }
//
//        // 取出整型数据中存在指定整数的数据(字串匹配)，并按照升序排列返回
//        printStringMatch(stringList);

//        test();

//        System.out.println(ShareApple(7,3));

//        whichWeek("2012-09-02");

//        isEmail("123@@qq.com");

//
//        Scanner scanner = new Scanner(System.in);
//        while (scanner.hasNext()) {
//            int n = scanner.nextInt();
//            System.out.println(n + ":" + isZhiShu(n));
//        }

        String str;
        int i, j, x, k = 0, temp, g = 0;
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        for (i = 0; i < 10; i++)  //将b数组初始化为0
            b[i] = 0;

        try {
            System.out.println("请输入元素个数 <10 以及各个元素值：");
            str = buf.readLine();
            x = Integer.parseInt(str); //x用来存放元素个数
            for (i = 0; i < x; i++) //输入各个元素值，每输完一个元素，按回车
            {
                str = buf.readLine();  //输入一个整数
                temp = Integer.parseInt(str);
                for (j = 0; j < k; j++) //检查该数字是否已经出现过
                {
                    if (a[j] == temp)  //该数字已经出现过，该数字的重数加1
                    {
                        b[j]++;
                        g = 1;
                        break;
                    }
                }
                if (g == 0) //该数字并未出现过，将该数字新添到a数组中，重数加1
                {
                    a[k] = temp;
                    b[k]++;
                    k++;
                }  //k用来表示不重复的元素个数
                g = 0;
            }

            temp = b[0];
            for (i = 1; i < k; i++)  //找出众数的重数
            {
                if (b[i] > temp) {
                    temp = b[i];
                }
            }
            System.out.println("众数为：");
            for (i = 0; i < k; i++)  //输出所有的众数
            {
                if (b[i] == temp)
                    System.out.println("" + a[i]); //显示元素个数，以及各个元素值
            }
            System.out.println("重数为：" + temp);  //显示这些众数的重数
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.currentTimeMillis();

        System.out.println("用时:" + (endTime - startTime));
    }


}
