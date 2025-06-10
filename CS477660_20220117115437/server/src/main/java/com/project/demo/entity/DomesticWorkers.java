package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *家政人员：(DomesticWorkers)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "DomesticWorkers")
public class DomesticWorkers implements Serializable {

    //DomesticWorkers编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domestic_workers_id")
    private Integer domestic_workers_id;
   // 姓名
   @Basic
    private String full_name;
   // 性别
   @Basic
    private String gender;
   // 电话
   @Basic
    private String telephone;
   // 头像
   @Basic
    private String head_portrait;
   // 个人简历
   @Basic
    private String curriculum_vitae;
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
