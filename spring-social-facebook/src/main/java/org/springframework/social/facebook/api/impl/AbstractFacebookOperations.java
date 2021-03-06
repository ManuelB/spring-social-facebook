/*
 * Copyright 2013 the original author or authors.
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
package org.springframework.social.facebook.api.impl;

import java.util.List;

import org.springframework.social.MissingAuthorizationException;

public class AbstractFacebookOperations {
	
	private final boolean isAuthorized;

	public AbstractFacebookOperations(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	protected void requireAuthorization() {
		if (!isAuthorized) {
			throw new MissingAuthorizationException("facebook");
		}
	}

	public String join(List<?> objects) {
		return join(objects, ",");

	}

	public String join(List<?> objects, String separator) {
		StringBuffer result = new StringBuffer();
		for (Object object : objects) {
			if (object == null) {
				continue;
			}
			if (result.length() > 0) {
				result.append(separator);
			}
			result.append(object.toString());
		}
		return result.toString();

	}
}
