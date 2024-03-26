package com.consult.analysis.service.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : laoa
 * @describe : 分页插件
 * @email : laoa@markcoin.net
 */
@Configuration
public class MyBatisConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        //1.初始化核心插件
        MybatisPlusInterceptor interceptor=new MybatisPlusInterceptor();
        //2.添加分页插件
        PaginationInnerInterceptor paginationInnerInterceptor=new PaginationInnerInterceptor(DbType.MYSQL);
        //3.设置分页上线
        paginationInnerInterceptor.setMaxLimit(1000l);
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}
