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
@Table(name = "tb_parameter")
public class ParameterEntity implements Serializable {

    private static final long serialVersionUID = -1785824516147698045L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 参数名
     * 例如Status, ID, AvgCarrierConc等
     * 不同模板由不同的参数组成
     */
    @Column(name="param_name")
    private String paramName;

    /**
     * 该设备对应的模板编号
     */
    @Column(name="template_id")
    private Long templateId;

}
