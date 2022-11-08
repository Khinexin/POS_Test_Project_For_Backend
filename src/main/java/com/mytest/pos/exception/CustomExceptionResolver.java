package com.mytest.pos.exception;

import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;

@Component
public class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {

	@Override
	protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
		if (ex instanceof DataNotFoundException) {
			return GraphqlErrorBuilder.newError().errorType(ErrorType.NOT_FOUND).message(ex.getMessage())
					.path(env.getExecutionStepInfo().getPath()).location(env.getField().getSourceLocation()).build();
		} else if (ex instanceof InvalidInputException) {
			return GraphqlErrorBuilder.newError().errorType(ErrorType.BAD_REQUEST).message(ex.getMessage())
					.path(env.getExecutionStepInfo().getPath()).location(env.getField().getSourceLocation()).build();
		} else if (ex instanceof PosException) {
			return GraphqlErrorBuilder.newError().errorType(ErrorType.INTERNAL_ERROR).message(ex.getMessage())
					.path(env.getExecutionStepInfo().getPath()).location(env.getField().getSourceLocation()).build();
		} else {
			return null;
		}
	}
}