package com.oi.oimall.interceptor;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import lombok.extern.log4j.Log4j2;

import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
@Log4j2
@Intercepts({@Signature(args = { MappedStatement.class, Object.class }, method = "update", type = Executor.class),
			 @Signature(args = { MappedStatement.class, RowBounds.class, ResultHandler.class }, method = "query", type = Executor.class)})
public class MapperIdInterceptor implements Interceptor {
	
	static final int MAPPED_STATEMENT_INDEX = 0;
	static final int PARAMETER_INDEX = 1;
	public static ThreadLocal<String> mapperId = new ThreadLocal<>();
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		final Object[] queryArgs = invocation.getArgs();
		final MappedStatement ms = (MappedStatement) queryArgs[MAPPED_STATEMENT_INDEX];
		String id = ms.getId();
		log.debug("#################### MapperIdInterceptor.interceptor id >> " + id);
		
		mapperId.set(String.format("id : %s", id));
		Object proceed = invocation.proceed();
		
		return proceed;
	}
	
	@Override
	public Object plugin(Object target) {
		// TODO Auto-generated method stub
		return Plugin.wrap(target, this);
	}
	
	@Override
	public void setProperties(Properties properties) {
		log.trace("setProperties : {}" + properties.toString());
	}



	
}
