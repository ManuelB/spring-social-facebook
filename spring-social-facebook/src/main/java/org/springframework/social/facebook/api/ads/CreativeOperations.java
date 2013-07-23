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
package org.springframework.social.facebook.api.ads;

import java.util.List;
<<<<<<< HEAD
=======

import org.springframework.social.facebook.api.Identifier;
>>>>>>> SOCIALFB-34 Deserialize List Return Values Properly

/**
 * @author Karthick Sankarachary
 */
public interface CreativeOperations {
	public List<AdCreative> getCreatives(String accountId);

	public AdCreative getCreative(String creativeId);

	public Identifier createCreative(String accountId, AdCreative creative);

	public boolean updateCreative(String creativeId, AdCreative creative);

	public boolean deleteCreative(String creativeId);
	
	public String getStory(String storyId);
}