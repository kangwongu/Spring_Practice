package com.springbasic.discount;

import com.springbasic.member.Grade;
import com.springbasic.member.Member;

public class FixDiscountPolicy implements DiscountPolicy {

    private static final int DISCOUNT_AMOUNT = 1000;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return DISCOUNT_AMOUNT;
        } else {
            return 0;
        }
    }
}
