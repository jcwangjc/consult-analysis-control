package com.consult.analysis.service.test.main;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * mybatis-plus代码生成器，生成实体，mapper，mapper.xml，service，serviceImpl，controller
 * 演示例子，执行 main 方法控制台输入表名回车自动生成对应项目目录中(目录要需要自行修改)
 */
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //获取项目路径  及代码生成器所在的项目路径
        String projectPath = "/Users/yangzhibang/workspace-gpt";
        //模块地址
        String modules = "/consult-analysis-control/consult-analysis-service";
        //设置输出文件路径
        gc.setOutputDir(projectPath +modules+"/src/main/java");
        //作者
        gc.setAuthor("laoA");
        //执行完 是否打开输出的目录，默认true
        gc.setOpen(false);
        //覆盖已有的文件，默认false(第一次生成时放开)
//        gc.setFileOverride(true);
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        // 设置日期类型为Date(若不设置时间类型都会变成LocalDateTime部分连接池例如druid是无法识别的)
        gc.setDateType(DateType.ONLY_DATE);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/xxl_job?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowPublicKeyRetrieval=true&useSSL=false");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("12345678");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //模块名称
        //pc.setModuleName(scanner("模块名"));
        //父包
        pc.setParent("com.consult.analysis.service");
        //自定义实体包名(不同的模块自己手动修改)
        pc.setEntity("domain");
        //自定义mapper包名(不同的模块自己手动修改)
        pc.setMapper("mapper");
        //自定义mapper.xml包名(不同的模块自己手动修改)
//        pc.setXml("mapper.xmls");
        //自定义service包名(不同的模块自己手动修改)
//        pc.setService("service");
        //自定义serviceImpl包名(不同的模块自己手动修改)
//        pc.setServiceImpl("service.impl");
        //自定义controller包名(不同的模块自己手动修改)
//        pc.setController("controller");

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String xmlPath = "/templates/mapper.xml.ftl";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出  例子如下
        focList.add(new FileOutConfig(xmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath +modules+ "/src/main/resources/mapper"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //是否为lombok模型，默认为false
        strategy.setEntityLombokModel(true);
        //前后端分离时可开启
//        strategy.setRestControllerStyle(true);
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        //RequestMapping驼峰转连字符
//        strategy.setControllerMappingHyphenStyle(true);
        //生成实体时生成生成数据库字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}

