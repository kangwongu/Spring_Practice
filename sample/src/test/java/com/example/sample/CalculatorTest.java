package com.example.sample;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void 덧셈_연산을_할_수_있다() {
        // given
        long num1 = 2;
        String operator = "+";
        long num2 = 3;
        Calculator calculator = new Calculator();

        // when
        long result = calculator.calculate(num1, operator, num2);

        // then
        Assertions.assertThat(result).isEqualTo(5);                 // assertj
        org.junit.jupiter.api.Assertions.assertEquals(5, result);   // junit
    }

    @Test
    public void 곱셈_연산을_할_수_있다() {
        // given
        long num1 = 2;
        String operator = "*";
        long num2 = 3;
        Calculator calculator = new Calculator();

        // when
        long result = calculator.calculate(num1, operator, num2);

        // then
        Assertions.assertThat(result).isEqualTo(6);                 // assertj
        org.junit.jupiter.api.Assertions.assertEquals(6, result);   // junit
    }

    @Test
    public void 뺄셈_연산을_할_수_있다() {
        // given
        long num1 = 2;
        String operator = "-";
        long num2 = 3;
        Calculator calculator = new Calculator();

        // when
        long result = calculator.calculate(num1, operator, num2);

        // then
        Assertions.assertThat(result).isEqualTo(-1);                 // assertj
        org.junit.jupiter.api.Assertions.assertEquals(-1, result);   // junit
    }

    @Test
    public void 나눗셈_연산을_할_수_있다() {
        // given
        long num1 = 6;
        String operator = "/";
        long num2 = 3;
        Calculator calculator = new Calculator();

        // when
        long result = calculator.calculate(num1, operator, num2);

        // then
        Assertions.assertThat(result).isEqualTo(2);                 // assertj
        org.junit.jupiter.api.Assertions.assertEquals(2, result);   // junit
    }

    @Test
    public void 잘못된_연산자가_요청으로_들어올_경우_에러가_난다() {
        // given
        long num1 = 6;
        String operator = "x";
        long num2 = 3;
        Calculator calculator = new Calculator();

        // when, then
        org.junit.jupiter.api.Assertions.assertThrows(InvalidOperatorException.class, () -> {
            calculator.calculate(num1, operator, num2);
        });
    }
}
