package com.fjd.servicedriveruser.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码工具类
 * @author: fanjidong R22496
 * @version: 1.0
 * @Date: 2023/3/15 7:26
 * @desc:
 */
public class MysqlGenerator {

    public static void main(String[] args) {

        //快速生成配置  create(url, userName, passWord)

        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-driver-user?characterEncoding=utf-8&serverTimeZone=GMT%2B8", "root", "root")
                //author 创建者名字， fileOverride 原来有就把它给覆盖了， outputDir 生成代码路径
                .globalConfig(builder -> {
                    builder.author("fanjidong").fileOverride().outputDir("E:\\project\\online-taxi-public\\online-taxi-public\\service-driver-user\\src\\main\\java");
                })
                //父包地址 和 生成mapper文件地址
                .packageConfig(builder -> {
                    builder.parent("com.fjd.servicedriveruser").pathInfo(Collections.singletonMap(OutputFile.mapper,
                            "E:\\project\\online-taxi-public\\online-taxi-public\\service-driver-user\\src\\main\\java\\com\\fjd\\servicedriveruser\\mapper"));
                })
                //生成策略
                .strategyConfig(builder -> {
                    //生成表名
                    builder.addInclude("driver_user_work_status");
                })
                //模板引擎
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
