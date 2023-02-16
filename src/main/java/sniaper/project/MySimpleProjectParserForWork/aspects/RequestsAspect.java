package sniaper.project.MySimpleProjectParserForWork.aspects;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
@Component("asp")
public class RequestsAspect {
	
	@Pointcut("execution(* showInfo())")
	public void show() {}
	
	public static String getedLog(String text) {
		String outputLog = null;
		try {
			outputLog = new String(text.getBytes(), "cp1251");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return outputLog;
	}
	
}
