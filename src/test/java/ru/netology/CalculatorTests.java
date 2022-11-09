package ru.netology;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class CalculatorTests {

    private Calculator calculator;

    @BeforeEach
    public void init() {
        System.out.println("Calculator test started");
        calculator = new Calculator();
    }

    @BeforeAll
    public static void started() {
        System.out.println("Calculator tests started");
    }

    @AfterEach
    public void finished() {
        System.out.println("Calculator test finished");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println("Calculator tests finished");
    }

    @Test
    public void testAddOperand_throwsIllegalStateException() {
        //given:
        String expectedMessage = "Formula is full of operands";
        //when:
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            calculator.newFormula()
                    .addOperand(1.00)
                    .addOperand(2.00)
                    .addOperand(3.00);
        });
        String actualMessage = exception.getMessage();
        //then:
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @ParameterizedTest
    @MethodSource("sourceForCalculateTest")
    public void testCalculate(double operand1, double operand2, Calculator.Operation op, double expected) {
        //when:
        double result = calculator.newFormula()
                .addOperand(operand1)
                .addOperand(operand2)
                .calculate(op)
                .result();
        //then:
        Assertions.assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("sourceForCalculateThrowsExceptionTest")
    public void testCalculate_throwsIllegalStateException(Calculator.Operation op) {
        //given:
        String expectedMessage = "Not enough operands!";
        //when:
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            calculator.newFormula()
                    .addOperand(1.00)
                    .calculate(op);
        });
        String actualMessage = exception.getMessage();
        //then:
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testResult_throwsIllegalStateException() {
        //given:
        String expectedMessage = "Formula is not computed!";
        //when:
        Exception exception = Assertions.assertThrows(IllegalStateException.class, () -> {
            calculator.newFormula()
                    .addOperand(1)
                    .addOperand(2)
                    .result();
        });
        String actualMessage = exception.getMessage();
        //then:
        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    private static Stream<Arguments> sourceForCalculateThrowsExceptionTest() {
        return Stream.of(
                Arguments.of(Calculator.Operation.SUM),
                Arguments.of(Calculator.Operation.SUB),
                Arguments.of(Calculator.Operation.MULT),
                Arguments.of(Calculator.Operation.DIV),
                Arguments.of(Calculator.Operation.POW));
    }

    private static Stream<Arguments> sourceForCalculateTest() {
        return Stream.of(
                Arguments.of(4.00, 2.00, Calculator.Operation.SUM, 6.00),
                Arguments.of(-4.00, 2.00, Calculator.Operation.SUM, -2.00),
                Arguments.of(-4.00, -6.00, Calculator.Operation.SUM, -10.00),
                Arguments.of(0.00, -6.00, Calculator.Operation.SUM, -6.00),
                Arguments.of(0.00, 0.00, Calculator.Operation.SUM, 0.00),
                Arguments.of(6.00, 3.00, Calculator.Operation.SUB, 3.00),
                Arguments.of(6.00, 10.00, Calculator.Operation.SUB, -4.00),
                Arguments.of(0.00, -6.00, Calculator.Operation.SUB, 6.00),
                Arguments.of(-12.00, -6.00, Calculator.Operation.SUB, -6.00),
                Arguments.of(-12.00, 6.00, Calculator.Operation.SUB, -18.00),
                Arguments.of(0.00, 0.00, Calculator.Operation.SUB, 0.00),
                Arguments.of(5.00, 6.00, Calculator.Operation.MULT, 30.00),
                Arguments.of(-3.00, 6.00, Calculator.Operation.MULT, -18.00),
                Arguments.of(-3.00, -5.00, Calculator.Operation.MULT, 15.00),
                Arguments.of(5.00, 0.00, Calculator.Operation.MULT, 0.00),
                Arguments.of(0.00, 5.00, Calculator.Operation.MULT, 0.00),
                Arguments.of(0.00, 0.00, Calculator.Operation.MULT, 0.00),
                Arguments.of(0.00, 0.00, Calculator.Operation.DIV, 0.00),
                Arguments.of(28.00, 4.00, Calculator.Operation.DIV, 7.00),
                Arguments.of(50.00, -5.00, Calculator.Operation.DIV, -10.00),
                Arguments.of(-42.00, -7.00, Calculator.Operation.DIV, 6.00),
                Arguments.of(5.00, 1.00, Calculator.Operation.POW, 5.00),
                Arguments.of(2.00, 3.00, Calculator.Operation.POW, 8.00),
                Arguments.of(3.00, 0.00, Calculator.Operation.POW, 1.00),
                Arguments.of(-3.00, 2.00, Calculator.Operation.POW, 9.00),
                Arguments.of(-3.00, 3.00, Calculator.Operation.POW, -27.00),
                Arguments.of(0.00, 3.00, Calculator.Operation.POW, 0.00),
                Arguments.of(4.00, -2.00, Calculator.Operation.POW, 0.0625));
    }
}
