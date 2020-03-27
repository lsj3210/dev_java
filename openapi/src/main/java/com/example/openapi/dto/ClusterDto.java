package com.example.openapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author lijian0423
 * @date 2020/3/27
 */
@Getter
@Setter
public class ClusterDto {
    @ApiModelProperty(value = "集群名称")
    private String name;
    @ApiModelProperty(value = "管理端地址")
    private String admin_addr;
    @ApiModelProperty(value = "绑定的VIP")
    private String bind_vip;
    @ApiModelProperty(value = "绑定的泛域名")
    private String bind_domain;
    @ApiModelProperty(value = "描述信息")
    private String description;
}
