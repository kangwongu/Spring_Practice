package com.springbasic.order;

import com.springbasic.discount.DiscountPolicy;
import com.springbasic.discount.FixDiscountPolicy;
import com.springbasic.member.Member;
import com.springbasic.member.MemberRepository;
import com.springbasic.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        /**
         * SRP 준수
         */
        Member findMember = memberRepository.findById(memberId);                // 회원 조회
        int discountPrice = discountPolicy.discount(findMember, itemPrice);     // 할인 금액 조회

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
