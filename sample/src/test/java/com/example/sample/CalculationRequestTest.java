package com.example.sample;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationRequestTest {

    @Test
    void 유효한_숫자를_파싱할_수_있다() {
        // given
        String[] parts = new String[]{"2", "+", "3"};

        //when
        CalculationRequest calculationRequest = new CalculationRequest(parts);

        // then
        assertEquals(2, calculationRequest.getNum1());
        assertEquals("+", calculationRequest.getOperator());
        assertEquals(3, calculationRequest.getNum2());
    }

    @Test
    void 세자리_숫자가_넘어가는_유효한_숫자를_파싱할_수_있다() {
        // given
        String[] parts = new String[]{"222", "+", "333"};

        //when
        CalculationRequest calculationRequest = new CalculationRequest(parts);

        // then
        assertEquals(222, calculationRequest.getNum1());
        assertEquals("+", calculationRequest.getOperator());
        assertEquals(333, calculationRequest.getNum2());
    }

    @Test
    void 유효한_길이의_숫자가_들어오지_않으면_에러를_던진다() {
        // given
        String[] parts = new String[]{"222", "+"};

        //when
        // then
        assertThrows(BadRequestException.class, () -> new CalculationRequest(parts));
    }

    @Test
    void 유효하지_않은_연산자가_들어오면_에러를_던진다() {
        // given
        String[] parts = new String[]{"222", "x", "333"};

        //when
        // then
        assertThrows(InvalidOperatorException.class, () -> new CalculationRequest(parts));

    }

    @Test
    void 유효하지_않은_길이의_연산자가_들어오면_에러를_던진다() {
        // given
        String[] parts = new String[]{"222", "+-", "333"};

        //when
        // then
        assertThrows(InvalidOperatorException.class, () -> new CalculationRequest(parts));

    }
}