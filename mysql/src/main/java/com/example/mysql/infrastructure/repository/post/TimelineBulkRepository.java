package com.example.mysql.infrastructure.repository.post;

import com.example.mysql.domain.post.entity.Timeline;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TimelineBulkRepository {

    private final JdbcTemplate jdbcTemplate;

    public void saveAllByBulk(List<Timeline> timelines) {
        String sql = "INSERT INTO timeline (member_id, post_id) " +
                "VALUES (?, ?)";

        jdbcTemplate.batchUpdate(sql,
                timelines,
                timelines.size(),
                (PreparedStatement ps, Timeline t) -> {
                    ps.setLong(1, t.getMemberId());
                    ps.setLong(2, t.getPostId());
                });
    }
}
