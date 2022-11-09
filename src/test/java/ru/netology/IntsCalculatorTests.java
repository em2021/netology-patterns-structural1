package ru.netology;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class IntsCalculatorTests {

    private Ints intsCalculator;

    @BeforeEach
    public void init() {
        System.out.println("IntsCalculator test started");
        intsCalculator = new IntsCalculator();
    }

    @BeforeAll
    public static void started() {
        System.out.println("IntsCalculator tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("IntsCalculator test finished");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("IntsCalculator tests finished");
    }

    @ParameterizedTest
    @MethodSource("sourceForSumTests")
    public void testSum(int operand1, int operand2, int expected) {
        //when:
        int result = intsCalculator.sum(operand1, operand2);
        //then:
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("sourceForSubTests")
    public void testSub(int operand1, int operand2, int expected) {
        //when:
        int result = intsCalculator.sub(operand1, operand2);
        //then:
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("sourceForMultTests")
    public void testMult(int operand1, int operand2, int expected) {
        //when:
        int result = intsCalculator.mult(operand1, operand2);
        //then:
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("sourceForDivTests")
    public void testDiv(int operand1, int operand2, int expected) {
        //when:
        int result = intsCalculator.div(operand1, operand2);
        //then:
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("sourceForPowTests")
    public void testPow(int operand1, int operand2, int expected) {
        //when:
        int result = intsCalculator.pow(operand1, operand2);
        //then:
        Assertions.assertEquals(expected, result);
    }

    public static Stream<Arguments> sourceForSumTests() {
        return Stream.of(
                Arguments.of(4, 2, 6),
                Arguments.of(-4, 2, -2),
                Arguments.of(-4, -6, -10),
                Arguments.of(0, -6, -6),
                Arguments.of(0, 0, 0));
    }

    public static Stream<Arguments> sourceForSubTests() {
        return Stream.of(
                Arguments.of(6, 3, 3),
                Arguments.of(6, 10, -4),
                Arguments.of(0, -6, 6),
                Arguments.of(-12, -6, -6),
                Arguments.of(-12, 6, -18),
                Arguments.of(0, 0, 0));
    }

    public static Stream<Arguments> sourceForMultTests() {
        return Stream.of(
                Arguments.of(5, 6, 30),
                Arguments.of(-3, 6, -18),
                Arguments.of(-3, -5, 15),
                Arguments.of(5, 0, 0),
                Arguments.of(0, 5, 0),
                Arguments.of(0, 0, 0));
    }

    public static Stream<Arguments> sourceForDivTests() {
        return Stream.of(
                Arguments.of(0, 0, 0),
                Arguments.of(28, 4, 7),
                Arguments.of(50, -5, -10),
                Arguments.of(-42, -7, 6));
    }

    public static Stream<Arguments> sourceForPowTests() {
        return Stream.of(
                Arguments.of(5, 1, 5),
                Arguments.of(2, 3, 8),
                Arguments.of(3, 0, 1),
                Arguments.of(-3, 2, 9),
                Arguments.of(-3, 3, -27),
                Arguments.of(0, 3, 0),
                Arguments.of(4, -2, 0));
    }
}
