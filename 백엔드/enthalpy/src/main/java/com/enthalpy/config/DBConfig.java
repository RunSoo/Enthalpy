package com.enthalpy.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.enthalpy.model.dao")
public class DBConfig {

}
