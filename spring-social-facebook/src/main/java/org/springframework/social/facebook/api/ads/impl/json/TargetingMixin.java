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

import java.util.List;

import org.springframework.social.facebook.api.ads.Name;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @author Karthick Sankarachary
 */
@JsonIgnoreProperties(ignoreUnknown = true)
abstract class TargetingMixin {

	@JsonCreator
	TargetingMixin() {
	}
	
	@JsonProperty("countries")
	List<String> countries;
	
	@JsonProperty("genders")
	List<Integer> genders;
	
	@JsonProperty("relationship_statuses")
	List<Integer> relationshipStatuses;
	
	@JsonProperty("age_min")
	int ageMin;
	
	@JsonProperty("age_max")
	int ageMax;
	
	@JsonProperty("cities")
	@JsonDeserialize(using=CitiesListDeserializer.class)
	List<Name> cities;
	
	@JsonProperty("regions")
	@JsonDeserialize(using=RegionsListDeserializer.class)
	List<Name> regions;
}
