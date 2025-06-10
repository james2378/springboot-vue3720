package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *服务项目：(ServiceItems)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ServiceItems")
public class ServiceItems implements Serializable {

    //ServiceItems编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_items_id")
    private Integer service_items_id;
   // 项目名称
   @Basic
    private String entry_name;
   // 项目类型
   @Basic
    private String project_type;
   // 服务详情
   @Basic
    private String service_details;
   // 项目海报
   @Basic
    private String project_poster;
   // 项目价格
   @Basic
    private String project_price;
    // 点击数
    @Basic
    private Integer hits;
    // 点赞数
    @Basic
    private Integer praise_len;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
