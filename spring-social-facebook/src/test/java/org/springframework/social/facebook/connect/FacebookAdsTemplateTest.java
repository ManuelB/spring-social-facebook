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
 */package org.springframework.social.facebook.connect;

import java.io.IOException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.social.facebook.api.ads.AccountGroupOperations;
import org.springframework.social.facebook.api.ads.AccountOperations;
import org.springframework.social.facebook.api.ads.AdCreative;
import org.springframework.social.facebook.api.ads.CampaignOperations;
import org.springframework.social.facebook.api.ads.CreativeOperations;
import org.springframework.social.facebook.api.ads.impl.FacebookAdsTemplate;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;

/**
 * Need to plugin real account and campaign ids prior to enabling tests.
 * 
 * @author Karthick Sankarachary
 */
public class FacebookAdsTemplateTest {
    private String accessToken = "CAAH62TEKjNcBAJtd3Ompv7B1g4ximbhu3SzowdminDULx6LrjjRKrm05cimPfOTFAeiJ1Txhc2fIrpzqQclz2RYPOlQ661AFFTbZAEcZB7g8SiBBOrblVopfrv8f0Yy6lsIk8xiDhtNHj62JIV";
    private String accountId = "61539687";
    private String campaignId;
    private String creativeId;
    private String accountGroupId;

    private FacebookAdsTemplate template;

    private ObjectMapper mapper;

    public FacebookAdsTemplateTest() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCreativeId() {
        return creativeId;
    }

    public void setCreativeId(String creativeId) {
        this.creativeId = creativeId;
    }

    public String getAccountGroupId() {
        return accountGroupId;
    }

    public void setAccountGroupId(String accountGroupId) {
        this.accountGroupId = accountGroupId;
    }

    @Before
    public void setUp() {
        this.template = new FacebookAdsTemplate(accessToken);
        this.mapper = new ObjectMapper();
    }

    @Test
    @Ignore
    public void testAccountOperations() throws JsonGenerationException,
            JsonMappingException, IOException {
        AccountOperations accountOps = template.accountOperations();
        mapper.writeValue(System.out, accountOps.getAccount(accountId));
        mapper.writeValue(System.out, accountOps.getAccountStats(accountId));
    }

    @Test
    @Ignore
    public void testAccountGroupOperations() throws JsonGenerationException,
            JsonMappingException, IOException {
        AccountGroupOperations accountGroupOps = template
                .accountGroupOperations();
        mapper.writeValue(System.out,
                accountGroupOps.getAccountGroup(accountGroupId));
    }

    @Test
    @Ignore
    public void testCampaignOperations() throws JsonGenerationException,
            JsonMappingException, IOException {
        CampaignOperations campaignOps = template.campaignOperations();
        mapper.writeValue(System.out, campaignOps.getCampaigns(accountId));
        mapper.writeValue(System.out, campaignOps.getCampaign(campaignId));
    }

    @Test
    @Ignore
    public void testCreativeOperations() throws JsonGenerationException,
            JsonMappingException, IOException {
        CreativeOperations creativeOps = template.creativeOperations();
        mapper.writeValue(System.out, creativeOps.getCreatives(accountId));
        mapper.writeValue(System.out, creativeOps.getCreative(creativeId));
    }

    @Test
    public void testCreativeCreateOperations() throws JsonGenerationException,
            JsonMappingException, IOException {
        CreativeOperations creativeOps = template.creativeOperations();
        AdCreative creative = new AdCreative();
        creative.setTitle("Hallo Welt");
        creative.setBody("Test 1234");
        mapper.writeValue(System.out, creativeOps.createCreative("123456", creative));
        
    }
}
