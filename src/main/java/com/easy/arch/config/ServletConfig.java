package com.easy.arch.config;

import com.easy.arch.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;


import java.util.List;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan("com.easy.arch")
public class ServletConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(thymeleafViewResolver());
    }

    @Bean
    public ThymeleafViewResolver thymeleafViewResolver(){
        ThymeleafViewResolver thymeleafViewResolver=new ThymeleafViewResolver();
        thymeleafViewResolver.setOrder(1);
        thymeleafViewResolver.setCharacterEncoding("UTF-8");
        thymeleafViewResolver.setTemplateEngine(getSpringTemplateEngine());
        return thymeleafViewResolver;
    }
    @Bean
    public SpringTemplateEngine getSpringTemplateEngine(){
        SpringTemplateEngine springTemplateEngine=new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(springResourceTemplateResolver());
        return springTemplateEngine;
    }
    @Bean
    public SpringResourceTemplateResolver springResourceTemplateResolver(){
        SpringResourceTemplateResolver templateResolver=new SpringResourceTemplateResolver();
        System.out.println("set spring resoucrce");
        templateResolver.setPrefix("/page/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }





//    @Bean
//    public InternalResourceViewResolver resourceViewResolver() {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        //请求页面文件的前缀地址
//        System.out.println(" set  preere");
//        internalResourceViewResolver.setPrefix("/page/");
//        //请求页面文件的后缀
//        internalResourceViewResolver.setSuffix(".html");
//        System.out.println(internalResourceViewResolver.toString());
//        return internalResourceViewResolver;
//    }
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        System.out.println("configigigi");
        configurer.enable();
    }

    //让spring 知道当前项目中的除了页面之外的静态资源都放在哪个目录下。
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/js/").addResourceLocations("/js/**");
//        registry.addResourceHandler("/css/").addResourceLocations("/css/**");
        registry.addResourceHandler("/static/*")
                .addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
//                .excludePathPatterns("classpath:/static/css/fonts/*.ttf")
//                .excludePathPatterns("classpath:/static/css/fonts/*.woff");
        registry.addInterceptor(getLoginInterceptor()).addPathPatterns("/success/**");
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
//        converters.add(new MappingJackson2XmlHttpMessageConverter());
    }
    @Bean
    public HandlerInterceptor getLoginInterceptor() {
        return new LoginInterceptor();
    }
}
