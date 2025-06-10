package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *订单报表：(OrderReport)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "OrderReport")
public class OrderReport implements Serializable {

    //OrderReport编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_report_id")
    private Integer order_report_id;
   // 总计收入
   @Basic
    private String aggregate_income_;
   // 报表日期
   @Basic
    private Timestamp report_date;
   // 项目名称
   @Basic
    private String entry_name;
   // 预约次数
   @Basic
    private Integer number_of_appointments;
   // 项目价格
   @Basic
    private Integer project_price;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
