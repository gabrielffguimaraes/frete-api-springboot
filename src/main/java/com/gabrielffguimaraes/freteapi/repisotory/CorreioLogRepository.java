package com.gabrielffguimaraes.freteapi.repisotory;

import com.gabrielffguimaraes.freteapi.entity.CorreioLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CorreioLogRepository extends JpaRepository<CorreioLog,Long> {
    public CorreioLog save(CorreioLog correioLog);
    public List<CorreioLog> findAll();
}
