package com.epam.framework.configuration.factory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.epam.framework.configuration.ConfigurationManager;
import com.epam.framework.configuration.ExcelManager;
import com.epam.framework.configuration.PropertiesManager;
import com.epam.framework.configuration.exception.ConfigurationException;
import com.epam.framework.logger.LoggerUtils;

public class ConfigurationFactory {

	private static ConfigurationFactory factory =null;
	
	private ConfigurationFactory(){
		
	}
	
	public static ConfigurationFactory getFactory(){
		if(factory == null){
			factory = new ConfigurationFactory();
		}
		return factory;
	}
	
	private interface Factory {
		ConfigurationManager create();
	}

	@SuppressWarnings("serial")
	private static final Map<Class<?>, Factory> factoryMap = Collections
			.unmodifiableMap(new HashMap<Class<?>, Factory>() {
				{
					put(ExcelManager.class, new Factory() {
						public ConfigurationManager create() {
							return ExcelManager.getInstance();
						}
					});
					put(PropertiesManager.class, new Factory() {
						public ConfigurationManager create() {
							return PropertiesManager.getInstance();
						}
					});
				}
			});

	
	public ConfigurationManager createManager(Class<?> className){
		Factory factory = factoryMap.get(className);
		if(factory == null){
			try {
				throw new ConfigurationException();
			} catch (ConfigurationException e) {
				LoggerUtils.error(e);
			}
		}
		return factory.create();
	}
}
