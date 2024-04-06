package com.springbasic.member;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    void join() {
        // given
        Member memberA = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(memberA);

        // then
        Member findMember = memberService.findMember(1L);
        assertThat(memberA).isEqualTo(findMember);

    }

}
