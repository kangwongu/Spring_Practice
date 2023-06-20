package com.example.mysql.infrastructure.repository.post;

import com.example.mysql.domain.post.dto.PostDto;
import com.example.mysql.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PostJpaRepository extends JpaRepository<Post, Long>, PostJpaRepositoryCustom {

    @Query("select new com.example.mysql.domain.post.dto.PostDto$DailyPostCountResponse(p.memberId, p.createdDate, count(*)) " +
            "from Post p " +
            "where p.memberId = :memberId " +
            "and p.createdDate between :firstDate and :lastDate " +
            "group by p.memberId, p.createdDate")
    List<PostDto.DailyPostCountResponse> groupByCreatedDate(@Param("memberId") Long memberId,
                                                            @Param("firstDate") LocalDate firstDate,
                                                            @Param("lastDate") LocalDate lastDate);

    @Query(value = "select p from Post p " +
            "where p.memberId = :memberId",
            countQuery = "select count(p) from Post p " +
                    "where p.memberId = :memberId"
    )
    Page<Post> findAllByMemberId(@Param("memberId") Long memberId,
                                 Pageable pageable);

}