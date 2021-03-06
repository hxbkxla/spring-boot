/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.aop.service;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import sample.annotation.Operation;

@Component
public class HelloWorldService {

	@Value("${name:World}")
	private String name;

	@Operation(name="getHelloMessage",date="fgffgdgf")
	public String getHelloMessage(String[] names,String sex) {
		return "Hello " + this.name;
	}

}
