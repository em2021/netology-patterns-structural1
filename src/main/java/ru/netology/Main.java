package ru.netology;

public class Main {
    public static void main(String[] args) {

        Ints calc = new IntsCalculator();
        System.out.println(calc.sum(15, 13));
        System.out.println(calc.sub(29, 10));
        System.out.println(calc.mult(3, 8));
        System.out.println(calc.div(9, 3));
        System.out.println(calc.pow(2, 3));
    }
}