package com.consult.analysis.service.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author laoA
 * @since 2024-02-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_information_vocabulary")
public class SystemAnalysisVocabulary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Long id;

    /**
     * 创建时间

     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 1-有效 2-失效
     */
    @TableField("status")
    private Integer status;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 资讯分类
     */
    @TableField("class_id")
    private Long classId;

    /**
     * word主分类
     */
    @TableField("root_name")
    private String rootName;
}
