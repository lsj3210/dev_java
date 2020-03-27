package com.example.openapi.controller;

import com.example.openapi.bean.ClusterEntity;
import com.example.openapi.dto.ClusterDto;
import com.example.openapi.services.ClusterService;
import com.example.openapi.utils.RequestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lijian0423
 * @date 2020/3/27
 */
@RestController
@RequestMapping("/rest/v1/cluster")
@Api(tags="集群管理",value = "集群的增删改查操作")
public class ClusterController {
    @Autowired
    private ClusterService clusterService;

    @ApiOperation(value = "新增集群接口",notes = "新增集群")
    @RequestMapping(value="/new",method = RequestMethod.POST )
    public RequestResult New(@RequestBody ClusterDto cluster){
        ClusterEntity entity = new ClusterEntity();
        entity.setName(cluster.getName());
        entity.setAdmin_addr(cluster.getAdmin_addr());
        entity.setBind_domain(cluster.getBind_domain());
        entity.setBind_vip(cluster.getBind_vip());
        entity.setDescription(cluster.getDescription());
        entity.setInit();
        entity = clusterService.saveOrUpdate(entity);
        return new RequestResult(entity);
    }

    @ApiOperation(value = "更新集群接口",notes = "更新集群")
    @RequestMapping(value="/modify/{id}",method = RequestMethod.POST )
    public RequestResult Modify(@PathVariable(name = "id", value = "id") Long id, @RequestBody ClusterDto cluster) {
        ClusterEntity entity = clusterService.findOne(id);
        entity.setName(cluster.getName());
        entity.setAdmin_addr(cluster.getAdmin_addr());
        entity.setBind_domain(cluster.getBind_domain());
        entity.setBind_vip(cluster.getBind_vip());
        entity.setDescription(cluster.getDescription());
        entity.setUpdate_date(System.currentTimeMillis()+"");
        entity = clusterService.saveOrUpdate(entity);
        return new RequestResult(entity);
    }

    @ApiOperation(value = "删除集群接口",notes = "删除集群")
    @RequestMapping(value="/delete/{id}",method = RequestMethod.DELETE)
    public RequestResult Delete(@PathVariable(name = "id", value = "id") Long id) {
        return null;
    }

}
