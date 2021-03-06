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

import java.util.List;

import org.springframework.social.facebook.api.GraphApi;
import org.springframework.social.facebook.api.Identifier;
import org.springframework.social.facebook.api.ads.AdGroup;
import org.springframework.social.facebook.api.ads.AdGroupOperations;
import org.springframework.social.facebook.api.ads.Id;
import org.springframework.social.facebook.api.ads.Stats;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author Karthick Sankarachary
 */
class AdGroupTemplate extends AbstractAdsOperations implements
		AdGroupOperations {

	public AdGroupTemplate(GraphApi graphApi, RestTemplate restTemplate,
			boolean isAuthorizedForUser) {
		super(graphApi, isAuthorizedForUser);
	}

	@Override
	public String[] getConnectionTypes() {
		return new String[] { "adaccount" };
	}

	public List<AdGroup> getAdGroups(String accountId) {
		requireAuthorization();
		return getConnections(getAccountId(accountId), AdGroup.class);
	}

	public AdGroup getAdGroup(String adGroupId) {
		requireAuthorization();
		return graphApi.fetchObject(adGroupId, AdGroup.class);
	}

	public List<AdGroup> getCampaignAdGroups(String campaignId) {
		requireAuthorization();
		return getConnections(getPath(campaignId, "adgroups"), AdGroup.class);
	}

	public Identifier createAdGroup(String accountId, AdGroup adGroup) {
		requireAuthorization();
		String id = graphApi.publish(getAccountId(accountId), "adgroups",
				getAdGroupData(adGroup));
		return new Identifier(id);
	}

	public boolean updateAdGroup(String adGroupId, AdGroup adGroup) {
		requireAuthorization();
		return graphApi.update(adGroupId, getAdGroupData(adGroup));
	}

	public boolean deleteAdGroup(String adGroupId) {
		requireAuthorization();
		String status = graphApi.delete(adGroupId);
		return Boolean.valueOf(status);
	}

	private MultiValueMap<String, Object> getAdGroupData(AdGroup adGroup) {
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<String, Object>();
		data.set("ad_id", adGroup.getAdId());
		data.set("campaign_id", adGroup.getCampaignId());
		data.set("name", adGroup.getName());
		data.set("adgroup_status", adGroup.getAdGroupStatus().getValue());
		data.set("bid_type", adGroup.getBidType().getValue());
		data.set("max_bid", adGroup.getMaxBid());
		data.set("targeting", adGroup.getTargeting());
		data.set("creative", "{'creative_id':"+adGroup.getCreative().getCreativeId()+"}");
		data.set("adgroup_id", adGroup.getAdGroupId());
		data.set("end_time", adGroup.getEndTime());
		data.set("start_time", adGroup.getStartTime());
		data.set("updated_time", adGroup.getUpdatedTime());
		data.set("bid_info", adGroup.getBidInfo());
		data.set("disapprove_reason_descriptions",
				adGroup.getDisapproveReasonDescriptions());
		return data;
	}

    public Stats getAdGroupStats(String adGroupId, long startTime, long endTime) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Stats> getAdGroupsStats(List<String> adGroupIds,
            long startTime, long endTime) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Stats> getAdGroupsStats(String accountId, long startTime,
            long endTime) {
        // TODO Auto-generated method stub
        return null;
    }

}
