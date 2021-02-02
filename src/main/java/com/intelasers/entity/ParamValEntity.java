package com.intelasers.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tb_param_val")
public class ParamValEntity implements Serializable {

    private static final long serialVersionUID = -1785824516147698045L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 本次记录的参数ID
     */
    @Column(name="param_id")
    private Long paramId;

    /**
     * 本次记录的参数值
     */
    @Column(name="param_val")
    private String paramVal;

    /**
     * 该设备对应的模板编号
     */
    @Column(name="template_id")
    private Long templateId;

    /**
     * 本次记录的设备编号
     */
    @Column(name="equipment_id")
    private Long equipmentId;

    /**
     * 本次数据的测试轮次
     */
    @Column(name="round_id")
    private Long roundId;


}
