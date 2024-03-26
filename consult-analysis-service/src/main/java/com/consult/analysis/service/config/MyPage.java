package com.consult.analysis.service.config;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author : laoa
 * @describe : 分页对象
 * @email : laoa@markcoin.net
 */
public class MyPage<T> extends Page<T> {

    /**
     * @Description: 将spring的分页对象转成Mybatis
     * @param pageable:
     * @return: null
     **/
    public MyPage(Pageable pageable) {
        this.setSize(pageable.getPageSize());
        this.setCurrent(pageable.getPageNumber() + 1);
    }
}

