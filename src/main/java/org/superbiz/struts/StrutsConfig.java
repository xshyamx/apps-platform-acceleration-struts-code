package org.superbiz.struts;

import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class StrutsConfig {

  @Bean
  @SuppressWarnings("deprecation")
  FilterRegistrationBean strutsCleanup() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new ActionContextCleanUp());
    registration.setOrder(1);
    registration.addUrlPatterns("/*");
    return registration;
  }

  @Bean
  @SuppressWarnings("deprecation")
  FilterRegistrationBean sitemesh() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    registration.setFilter(new PageFilter());
    registration.setOrder(2);
    registration.addUrlPatterns("/*");
    return registration;
  }

  @SuppressWarnings("deprecation")
  FilterRegistrationBean struts2(@Value("${struts.action.packages}") List<String> actionPackages) {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    Map<String, String> initParams = new HashMap<>();
    initParams.put("actionPackages", actionPackages.stream().collect(Collectors.joining(",")));
    registration.setFilter(new FilterDispatcher());
    registration.setInitParameters(initParams);
    registration.setOrder(3);
    registration.addUrlPatterns("/*");
    return registration;
  }

  @Bean
  @SuppressWarnings("deprecation")
  FilterRegistrationBean struts2() {
    FilterRegistrationBean registration = new FilterRegistrationBean();
    Map<String, String> initParams = new HashMap<>();
    initParams.put("actionPackages", "com.lq");
    registration.setFilter(new FilterDispatcher());
    registration.setInitParameters(initParams);
    registration.setOrder(3);
    registration.addUrlPatterns("/*");
    return registration;
  }

}
