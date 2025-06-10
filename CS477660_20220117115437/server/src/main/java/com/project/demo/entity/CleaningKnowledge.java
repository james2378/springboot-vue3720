package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *保洁常识：(CleaningKnowledge)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CleaningKnowledge")
public class CleaningKnowledge implements Serializable {

    //CleaningKnowledge编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cleaning_knowledge_id")
    private Integer cleaning_knowledge_id;
   // 标题
   @Basic
    private String title;
   // 详情
   @Basic
    private String details;
   // 标签
   @Basic
    private String label;
   // 封面图
   @Basic
    private String cover_picture;
   // 发布时间
   @Basic
    private Timestamp release_time;
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
