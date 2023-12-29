package com.example.springstart.member.infrastructure;

import com.example.springstart.member.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterAll() {
        repository.clearStore();
    }

    @Test
    @DisplayName("save 메소드 테스트")
    public void save() {
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertThat(result).isEqualTo(member);
    }

    @Test
    @DisplayName("findByName 메소드 테스트")
    public void findByName() {
        // given
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        // when
        Member findMember = repository.findByName("Spring1").get();

        // then
        assertThat(findMember).isEqualTo(member1);
    }

    @Test
    public void findAll_테스트() {
        // given
        Member member1 = new Member();
        member1.setName("Spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring2");
        repository.save(member2);

        // when
        List<Member> members = repository.findAll();

        // then
        assertThat(members.size()).isEqualTo(2);
    }
}