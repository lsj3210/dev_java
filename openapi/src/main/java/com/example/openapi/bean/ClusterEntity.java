package com.example.openapi.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_cluster")
@org.hibernate.annotations.Table(appliesTo = "tb_cluster",comment="集群信息表")
public class ClusterEntity extends IdOperatorEntity implements Serializable {
    private String name;
    private String admin_addr;
    private String bind_vip;
    private String bind_domain;
    private String description;

    @Column(columnDefinition = "varchar(64) not null unique comment '集群名称'")
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    @Column(columnDefinition = "varchar(128) not null comment '集群管理API地址'")
    public String getAdmin_addr() {
        return admin_addr;
    }
    public void setAdmin_addr(String admin_addr) {
        this.admin_addr = admin_addr;
    }

    @Column(columnDefinition = "varchar(128) not null comment '集群负载VIP'")
    public String getBind_vip() {
        return bind_vip;
    }
    public void setBind_vip(String bind_vip) {
        this.bind_vip = bind_vip;
    }

    @Column(columnDefinition = "varchar(16) not null comment '集群绑定泛域名'")
    public String getBind_domain() {
        return bind_domain;
    }
    public void setBind_domain(String bind_domain) {
        this.bind_domain = bind_domain;
    }

    @Column(columnDefinition = "varchar(2048) comment '集群描述信息'")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
