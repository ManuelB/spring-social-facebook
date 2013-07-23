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
package org.springframework.social.facebook.api.ads.impl.json;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers.DateDeserializer;



/**
 * @author Karthick Sankarachary
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class AdCampaignMixin {

	@JsonCreator
	AdCampaignMixin() {
	}

	@JsonProperty("account_id")
	long accountId;

	@JsonProperty("start_time")
	@JsonDeserialize(using=DateDeserializer.class)
	Date startTime;

	@JsonProperty("end_time")
	@JsonDeserialize(using=DateDeserializer.class)
	int endTime;

	@JsonProperty("daily_budget")
	int dailyBudget;

	@JsonProperty("campaign_status")
	int campaignStatus;

	@JsonProperty("lifetime_budget")
	int lifetimeBudget;

}
