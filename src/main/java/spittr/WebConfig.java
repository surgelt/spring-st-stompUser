package spittr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
@EnableWebMvc
@ComponentScan
@EnableScheduling
public class WebConfig extends WebMvcConfigurerAdapter
{
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("/").setViewName("home");
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry)
    {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    // THYMELEAF CONFIG
    @Bean
    public ViewResolver viewResolver(SpringTemplateEngine templateEngine)
    {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine);
        return viewResolver;
    }
    
    // @Bean
    // public SpringTemplateEngine templateEngine(TemplateResolver templateResolver) {
    // SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    // templateEngine.setTemplateResolver(templateResolver);
    // return templateEngine;
    // }
    //
    // @Bean
    // public TemplateResolver templateResolver() {
    // TemplateResolver templateResolver = new ServletContextTemplateResolver();
    // templateResolver.setPrefix("/WEB-INF/views/");
    // templateResolver.setSuffix(".html");
    // templateResolver.setTemplateMode("HTML5");
    // return templateResolver;
    // }
    
    @Bean
    public SpringTemplateEngine templateEngine()
    {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }
    
    @Bean
    public ITemplateResolver templateResolver()
    {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("utf-8");
        templateResolver.setOrder(1);
        
        templateResolver.setCacheable(false);
        return templateResolver;
    }
    
}
