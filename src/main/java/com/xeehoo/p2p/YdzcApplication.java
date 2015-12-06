package com.xeehoo.p2p;

import com.xeehoo.p2p.filter.CacheFilter;
import com.xeehoo.p2p.interceptor.LoginInterceptor;
import com.xeehoo.p2p.interceptor.SecurityInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.sql.DataSource;

@EnableAutoConfiguration
@SpringBootApplication
@PropertySources(value = {@PropertySource("classpath:application.properties")})
@MapperScan(basePackages= "com.xeehoo.p2p.mybatis.mapper")
public class YdzcApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(YdzcApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
//                .excludePathPatterns("/error")
//                .excludePathPatterns("/login")
//                .excludePathPatterns("/ajax/login")
//                .excludePathPatterns("/ajax/upload")
//                .excludePathPatterns("/ajax/mobile")
//                .excludePathPatterns("/logout")
//                .excludePathPatterns("/reguser")
//                .excludePathPatterns("/reg2")
                .addPathPatterns("/fund");
        registry.addInterceptor(new SecurityInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/staff");


//        super.addInterceptors(registry);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sessionFactory.setMapperLocations(resolver
//                .getResources("classpath:com/xeehoo/p2p/mybatis/mapper/*.xml"));
        return sessionFactory.getObject();
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(cacheFilter());
//        registration.addUrlPatterns("/url/*");
//        registration.addInitParameter("paramName", "paramValue");
//        registration.setName("someFilter");
        return registration;
    }

    @Bean(name = "cacheFilter")
    public Filter cacheFilter() {
        return new CacheFilter();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
    }

//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setMaxFileSize("128KB");
//        factory.setMaxRequestSize("128KB");
//        return factory.createMultipartConfig();
//    }

//    @Bean
//    public DispatcherServlet dispatcherServlet() {
//        return new DispatcherServlet();
//    }
//
//    /**
//     * Register dispatcherServlet programmatically
//     *
//     * @return ServletRegistrationBean
//     */
//    @Bean
//    public ServletRegistrationBean dispatcherServletRegistration() {
//        ServletRegistrationBean registration = new ServletRegistrationBean(
//                dispatcherServlet(), "/*");
//
//        registration
//                .setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
//
//        return registration;
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("/");
//    }
}
