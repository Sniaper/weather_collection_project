package sniaper.project.MySimpleProjectParserForWork.aspects;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import sniaper.project.MySimpleProjectParserForWork.Detective;

@Component
@Aspect
@Order(2)
public class afterAspects {
	@After("execution(* getNameDetective())")
	public void afterGenNameCity() {
		System.out.println(sniaper.project.MySimpleProjectParserForWork.
				aspects.RequestsAspect.getedLog("Данные Детектива пепреданы..."));
		Detective.addRequest();
	}
}
