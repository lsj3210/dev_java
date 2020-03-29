package com.example.openapi.services.impl;

import com.example.openapi.bean.ClusterEntity;
import com.example.openapi.dao.ClusterDao;
import com.example.openapi.services.ClusterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * @author lijian0423
 * @date 2020/3/27
 */
@Service
public class ClusterServiceImpl extends DBaseServiceImpl<ClusterEntity> implements ClusterService {
    private final Logger logger = LoggerFactory.getLogger(ClusterServiceImpl.class);

    @Autowired
    private ClusterDao clusterDao;

    @Override
    public JpaRepository<ClusterEntity, Long> getBaseDao() {
        return this.clusterDao;
    }
}
