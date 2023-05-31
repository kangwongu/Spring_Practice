package com.example.mysql.domain.post.repository;

import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select new com.example.mysql.domain.post.dto.PostDto.DailyPostCountResponse(p.memberId, p.createdDate, count(*)) " +
            "from Post p " +
            "where p.memberId = :memberId " +
            "and p.createdDate between :firstDate and :lastDate " +
            "group by p.memberId, p.createdDate")
    List<PostDto.DailyPostCountResponse> groupByCreatedDate(@Param("memberId") Long memberId,
                                                            @Param("firstDate") LocalDate firstDate,
                                                            @Param("lastDate") LocalDate lastDate);
}
