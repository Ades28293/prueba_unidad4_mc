package com.uce.edu.demo.service.funcional;
@FunctionalInterface
public interface IFunction<R,T> {
		
	public R apply(T arga1);
}
