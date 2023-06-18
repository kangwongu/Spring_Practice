package com.example.mysql.domain.post.repository;

import com.example.mysql.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAllByBulk(List<Post> posts) {
        String sql = "INSERT INTO post (member_id, contents, created_date, created_at) " +
                "VALUES (?, ?, ?, ?)";

        jdbcTemplate.batchUpdate(sql,
                posts,
                posts.size(),
                (PreparedStatement ps, Post p) -> {
                    ps.setLong(1, p.getMemberId());
                    ps.setString(2, p.getContents());
                    ps.setDate(3, Date.valueOf(p.getCreatedDate()));
                    ps.setTimestamp(4, p.getCreatedAt() == null ? Timestamp.valueOf(LocalDateTime.now()) : Timestamp.valueOf(p.getCreatedAt()));
                });
    }

}
