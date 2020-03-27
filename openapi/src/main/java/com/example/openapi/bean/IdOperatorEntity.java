package com.example.openapi.bean;

import javax.persistence.*;

@MappedSuperclass
public class IdOperatorEntity {
    private Long id;
    private String create_user;
    private String create_date;
    private String update_user;
    private String update_date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(columnDefinition = "varchar(64) comment '创建人'")
    public String getCreate_user() {
        return create_user;
    }
    public void setCreate_user(String create_user) {
        this.create_user = create_user;
    }

    @Column(columnDefinition = "varchar(32) not null comment '创建时间(时间戳)'")
    public String getCreate_date() {
        return create_date;
    }
    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    @Column(columnDefinition = "varchar(64) comment '最近更新人'")
    public String getUpdate_user() {
        return update_user;
    }
    public void setUpdate_user(String update_user) {
        this.update_user = update_user;
    }

    @Column(columnDefinition = "varchar(32) not null comment '最近更新时间(时间戳)'")
    public String getUpdate_date() {
        return update_date;
    }
    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }


    public void setInit() {
        this.create_date = System.currentTimeMillis()+"";
        this.update_date = System.currentTimeMillis()+"";
    }

}
