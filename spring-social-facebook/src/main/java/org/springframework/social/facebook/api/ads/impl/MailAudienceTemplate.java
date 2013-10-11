/*
 * Copyright 2010 the original author or authors.
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
package org.springframework.social.facebook.api.ads.impl;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.ads.Id;
import org.springframework.social.facebook.api.ads.MailAudience;
import org.springframework.social.facebook.api.ads.MailAudienceOperations;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * @author Hardy Uehlemann
 */
class MailAudienceTemplate extends AbstractAdsOperations implements
		MailAudienceOperations {

	public MailAudienceTemplate(GraphApi graphApi, boolean isAuthorizedForUser) {
		super(graphApi, isAuthorizedForUser);
	}

	@Override
	public String[] getConnectionTypes() {
		return new String[] { "adaccount", "customaudience" };
	}

	public Id createMailAudience(String accountId, MailAudience mailAudience) {
		requireAuthorization();
		String id = graphApi.publish(getAccountId(accountId), "customaudiences",
				getMailAudienceData(mailAudience));
		return new Id(id);
	}

	private MultiValueMap<String, Object> getMailAudienceData(MailAudience mailAudience) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("name", mailAudience.getName());
		data.set("description", mailAudience.getDescription());
		data.set("users", mailAudience.getEmails());
		return data;
	}

}
