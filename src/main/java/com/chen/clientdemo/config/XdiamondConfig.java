package com.chen.clientdemo.config;

import io.github.xdiamond.client.spring.XDiamondConfigFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.transaction.xa.Xid;
import java.util.Properties;

@Configuration
public class XdiamondConfig implements EnvironmentAware {

    private final static Logger LOGGER = LoggerFactory.getLogger(XdiamondConfig.class);

    private Environment environment;

    @Bean
    public XDiamondConfigFactoryBean xDiamondConfigFactoryBean() {

        /**
         *  <property name="serverHost" value="localhost" />
         <property name="serverPort" value="5678" />
         <property name="groupId" value="com.chen" />
         <property name="artifactId" value="clientdemo" />
         <property name="version" value="0.0.1-SNAPSHOT" />
         <property name="profile" value="dev" />
         <property name="secretKey" value="123456"></property>
         */

        XDiamondConfigFactoryBean xDiamondConfigFactoryBean = new XDiamondConfigFactoryBean();
        xDiamondConfigFactoryBean.setArtifactId(environment.getProperty("xdiamond.client.artifactid"));
        xDiamondConfigFactoryBean.setGroupId(environment.getProperty("xdiamond.client.groupid"));
        xDiamondConfigFactoryBean.setVersion(environment.getProperty("xdiamond.client.version"));
        xDiamondConfigFactoryBean.setProfile(environment.getProperty("xdiamond.client.profile"));
        xDiamondConfigFactoryBean.setSecretKey(environment.getProperty("xdiamond.client.secretkey"));

        xDiamondConfigFactoryBean.setServerHost(environment.getProperty("xdiamond.server.host"));
        xDiamondConfigFactoryBean.setServerPort(environment.getProperty("xdiamond.server.port"));

        return xDiamondConfigFactoryBean;
    }

//    @Bean
//    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
//        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
//        try {
//            propertyPlaceholderConfigurer.setProperties(xDiamondConfigFactoryBean().getObject().getProperties());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return propertyPlaceholderConfigurer;
//    }

    @Override
    public void setEnvironment(Environment environment) {
        String artifactid = environment.getProperty("xdiamond.client.artifactid");
        LOGGER.info("xdiamond.client.artifactid =  "+artifactid);
        String groupid = environment.getProperty("xdiamond.client.groupid");
        LOGGER.info("xdiamond.client.groupid =  "+groupid);

        this.environment = environment;
    }
}
