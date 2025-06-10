package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *预约：(MakeAnAppointment)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "MakeAnAppointment")
public class MakeAnAppointment implements Serializable {

    //MakeAnAppointment编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "make_an_appointment_id")
    private Integer make_an_appointment_id;
   // 项目名称
   @Basic
    private String entry_name;
   // 项目类型
   @Basic
    private String project_type;
   // 项目价格
   @Basic
    private String project_price;
   // 用户
   @Basic
    private Integer user;
   // 上门时间
   @Basic
    private Timestamp door_time;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
