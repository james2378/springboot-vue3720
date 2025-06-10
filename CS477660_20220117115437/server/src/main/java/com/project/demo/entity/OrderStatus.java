package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *订单状态：(OrderStatus)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OrderStatus")
public class OrderStatus implements Serializable {

    //OrderStatus编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_status_id")
    private Integer order_status_id;
   // 项目名称
   @Basic
    private String entry_name;
   // 项目类型
   @Basic
    private String project_type;
   // 服务价格
   @Basic
    private String service_price;
   // 用户
   @Basic
    private Integer user;
   // 预约时间
   @Basic
    private String time_of_appointment;
   // 订单状态
   @Basic
    private String order_status;
   // 服务人员
   @Basic
    private Integer service_personal;
   // 备注
   @Basic
    private String remarks;
    // 支付状态
    @Basic
    private String pay_state;

    // 支付类型: 微信、支付宝、网银
    @Basic
    private String pay_type;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
