package com.springbasic;

import com.springbasic.discount.DiscountPolicy;
import com.springbasic.discount.RateDiscountPolicy;
import com.springbasic.member.MemberRepository;
import com.springbasic.member.MemberService;
import com.springbasic.member.MemberServiceImpl;
import com.springbasic.member.MemoryMemberRepository;
import com.springbasic.order.OrderService;
import com.springbasic.order.OrderServiceImpl;

// 각 클래스에 필요한 세팅을 해주는 클래스
public class AppConfig {

    // 역할을 드러낸다

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
