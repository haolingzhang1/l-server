package com.intelasers.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_param_val")
public class TemplateEntity {

    private static final long serialVersionUID = -1785824516147698045L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 模板名称
     */
    @Column(name="template_name")
    private String templateName;

    /**
     * 对模板的描述
     */
    @Column(name="template_desc")
    private String templateDesc;


    /**
     * 创建时间
     */
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @LastModifiedDate
    @Column(name = "modify_time")
    private Date modifyTime;


}
