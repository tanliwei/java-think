package cn.tanlw.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * 1、编写函数，实现输出1000以内的素数的功能。
 */
public class PrimeNumber {

    public static List<Integer> getPrimeNumbers(int end) {
        List<Integer> primeNumbers = new ArrayList(end / 10);
        boolean isPrimeNumber;
        for (int number = 2; number <= end; number++) {
            isPrimeNumber = true;
            for (int j = 2; j <= Math.sqrt(number); j++) {
                if (number % j == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }
            if (isPrimeNumber) {
                primeNumbers.add(number);
            }
        }
        return primeNumbers;
    }

    public static List<Integer> getPrimeNumbers2(int end) {
        List<Integer> primeNumbers = new ArrayList(end / 10);
        boolean isPrimeNumber;
        primeNumbers.add(2);
        //外部排除偶数
        for (int number = 3; number <= end; number += 2) {
            isPrimeNumber = true;
            //外部排除偶数
            for (int j = 3; j <= Math.sqrt(number); j += 2) {
                if (number % j == 0) {
                    isPrimeNumber = false;
                    break;
                }
            }
            if (isPrimeNumber) {
                primeNumbers.add(number);
            }
        }
        return primeNumbers;
    }

    public static void main(String[] args) {
        List<Integer> list = PrimeNumber.getPrimeNumbers2(1000);
        System.out.println("size:"+list.size());
        System.out.println("list:"+list);
    }
}
