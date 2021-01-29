package com.intelasers.entity;

import lombok.Data;
import java.io.Serializable;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * @author ling
 */
@Data
public class ResultEntity implements Serializable {
    private static final long serialVersionUID = 752386055477259305L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 返回结果
     */
    private boolean isSuccess;

    /**
     * 返回code
     */
    private int status;
    /**
     * 返回数据
     */
    private Object data;

}