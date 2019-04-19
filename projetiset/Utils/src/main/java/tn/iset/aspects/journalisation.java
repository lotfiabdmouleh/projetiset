package tn.iset.aspects;

 

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class journalisation {
 
	@Pointcut("execution(public * *(..))")
	private void anyPublicOperation() {
		System.out.println("before");
	}
	 
}
