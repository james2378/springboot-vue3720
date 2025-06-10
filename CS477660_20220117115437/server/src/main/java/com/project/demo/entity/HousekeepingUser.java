package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *家政用户：(HousekeepingUser)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "HousekeepingUser")
public class HousekeepingUser implements Serializable {

    //HousekeepingUser编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "housekeeping_user_id")
    private Integer housekeeping_user_id;
   // 性别
   @Basic
    private String gender;
   // 所在小区
   @Basic
    private String community;
   // 现居住址
   @Basic
    private String current_address;
   // 家政公司
   @Basic
    private String domestic_companies_;
   // 认证图片
   @Basic
    private String authentication_picture;
    // 审核状态
    @Basic
    private String examine_state;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
