package org.hxb.SVNTEST;

import java.text.SimpleDateFormat;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;



/**
 * json转换
 * @类名：ECSJsonUtil
 * @作者:wanhonghui
 * @日期：2015-9-16 上午11:38:37
 */
public class IECSJsonUtil {
	/**
	 * 转换json
	 * @作者：wanhonghui
	 * @日期：2015-9-16 上午11:38:25
	 * @param value
	 * @return
	 * @throws IECSBaseException 
	 */
	public static String coventJson(Object value,String [] ignore) throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		if(null!=ignore  && ignore.length>0){
			SimpleBeanPropertyFilter theFilter = SimpleBeanPropertyFilter.serializeAllExcept(ignore);
		    FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", theFilter);
		    objectMapper.setFilters(filters);
		}
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		String result = null;
		try {
			objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); 
			result = objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
