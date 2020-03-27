package com.example.openapi.dao;

import com.example.openapi.bean.ClusterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author lijian0423
 * @date 2020/3/27
 */
public interface ClusterDao extends JpaRepository<ClusterEntity, Long> {
}
