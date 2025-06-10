package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *核酸检测：(NucleicAcidTest)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "NucleicAcidTest")
public class NucleicAcidTest implements Serializable {

    //NucleicAcidTest编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nucleic_acid_test_id")
    private Integer nucleic_acid_test_id;
   // 体温
   @Basic
    private String temperature;
   // 家政人员
   @Basic
    private Integer domestic_workers;
   // 联系电话
   @Basic
    private String contact_number;
   // 所在小区
   @Basic
    private String community;
   // 详细地址
   @Basic
    private String detailed_address;
   // 核酸日期
   @Basic
    private Timestamp nucleic_acid_date;
   // 核酸报告
   @Basic
    private String nucleic_acid_report;
   // 健康码
   @Basic
    private String health_code;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
