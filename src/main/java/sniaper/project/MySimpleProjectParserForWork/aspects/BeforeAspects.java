package sniaper.project.MySimpleProjectParserForWork.aspects;

import java.io.UnsupportedEncodingException;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class BeforeAspects{
	
	@Before("sniaper.project.MySimpleProjectParserForWork.aspects.RequestsAspect.show()")
	public void beforeGetInfo(JoinPoint joinPoint) {
		System.out.println(RequestsAspect.getedLog("Запрос информации..."));
		
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
		try {
			System.out.println(methodSignature.getName() + 
					new String(" - названия метода".getBytes(), "cp1251"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
