package com.spring.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
 * Handle message Internationalization (i18n)
 *
 */
@Component
public class MessageHandler {
	@Autowired
	ApplicationContext applicationContext;

	/**
	 * Try to resolve the message. Treat as an error if the message can't be found.
	 * @param code
	 * @return String
	 */
	public String getMessage(String code) {
		return applicationContext.getMessage(code, null, Locale.ENGLISH);
	}

	/**
	 *Try to resolve the message. Treat as an error if the message can't be found.
	 * @param code the code to lookup up, such as 'calculator.noRateSet'
	 * @param objs Array of arguments that will be filled in for params within
	 * the message (params look like "{0}", "{1,date}", "{2,time}" within a message),
	 * or {@code null} if none.
	 * @return
	 */
	public String getMessage(String code, Object[] objs) {
		return applicationContext.getMessage(code, objs, Locale.ENGLISH);
	}
}
