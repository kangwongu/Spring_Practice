package com.springbasic.order;

import static org.assertj.core.api.Assertions.*;

import com.springbasic.AppConfig;
import com.springbasic.member.Grade;
import com.springbasic.member.Member;
import com.springbasic.member.MemberService;
import com.springbasic.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    void setUp() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        // given
        Long memberId = 1L;
        Member memberA = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(memberA);

        // when
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
