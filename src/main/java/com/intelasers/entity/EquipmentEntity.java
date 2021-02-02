package com.intelasers.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_equipment")
@EntityListeners(AuditingEntityListener.class)
public class EquipmentEntity implements Serializable {


    private static final long serialVersionUID = -1785824516147698045L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 设备名-编号:
     * Epitaxy equipment number
     * 例如GA-01
     */
    @Column(name="unit")
    private String unit;

    /**
     * 序列号:
     * Run number
     */
    @Column(name="seq")
    private String seq;

    /**
     *
     * Pkt/晶圆编号:
     * wafer number, 01， 02， 03...
     */
    @Column(name="pkt")
    private String pkt;

    /**
     * 该设备对应的模板编号
     */
    @Column(name="template_id")
    private Long templateId;

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
