package sniaper.project.MySimpleProjectParserForWork;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("sniaper.project.MySimpleProjectParserForWork")
@PropertySource("classpath:App.properties")
@EnableAspectJAutoProxy
public class ClassConfigBean {
	
}
