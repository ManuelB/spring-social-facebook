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

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.facebook.api.ads.AccountGroupOperations;
import org.springframework.social.facebook.api.ads.AccountOperations;
import org.springframework.social.facebook.api.ads.AdGroupOperations;
import org.springframework.social.facebook.api.ads.CampaignOperations;
import org.springframework.social.facebook.api.ads.CreativeOperations;
import org.springframework.social.facebook.api.ads.FacebookAds;
import org.springframework.social.facebook.api.ads.impl.json.FacebookAdsModule;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Karthick Sankarachary
 */
public class FacebookAdsTemplate extends FacebookTemplate implements
        FacebookAds {
    private AccountOperations      accountOperations;
    private AccountGroupOperations accountGroupOperations;
    private CampaignOperations     campaignOperations;
    private CreativeOperations     creativeOperations;
    private AdGroupOperations      adGroupOperations;

    public FacebookAdsTemplate() {
        super();
        initialize();
    }

    public FacebookAdsTemplate(String accessToken) {
        super(accessToken);
        initialize();
    }

    private void initialize() {
        initSubApis();
    }

    private void initSubApis() {
        accountOperations = new AccountTemplate(this, getRestTemplate(),
                isAuthorized());
        accountGroupOperations = new AccountGroupTemplate(this, isAuthorized());
        campaignOperations = new CampaignTemplate(this, isAuthorized());
        creativeOperations = new CreativeTemplate(this, isAuthorized());
        adGroupOperations = new AdGroupTemplate(this, getRestTemplate(),
                isAuthorized());
    }

    @Override
    protected FormHttpMessageConverter getFormMessageConverter() {
        // FormHttpMessageConverter formHttpMessageConverter = super
        // .getFormMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();

        FormHttpMessageConverter converter = new FormHttpMessageConverter() {
            @Override
            public void write(MultiValueMap<String, ?> form,
                    MediaType contentType, HttpOutputMessage outputMessage)
                    throws IOException, HttpMessageNotWritableException {
                Charset charset;
                if (contentType != null) {
                    outputMessage.getHeaders().setContentType(contentType);
                    charset = contentType.getCharSet() != null ? contentType
                            .getCharSet() : Charset.defaultCharset();
                } else {
                    outputMessage.getHeaders().setContentType(
                            MediaType.APPLICATION_FORM_URLENCODED);
                    charset = Charset.defaultCharset();
                }
                StringBuilder builder = new StringBuilder();
                for (Iterator<String> nameIterator = form.keySet().iterator(); nameIterator
                        .hasNext();) {
                    String name = nameIterator.next();
                    for (Iterator<?> valueIterator = form.get(name).iterator(); valueIterator
                            .hasNext();) {

                        Object o = valueIterator.next();

                        String value = o == null ? null : o.toString();

                        if (o != null && !(o instanceof String)
                                && !(o instanceof Integer)) {
                            value = objectMapper.writeValueAsString(o);
                        }

                        builder.append(URLEncoder.encode(name, charset.name()));
                        if (value != null) {
                            builder.append('=');
                            builder.append(URLEncoder.encode(value,
                                    charset.name()));
                            if (valueIterator.hasNext()) {
                                builder.append('&');
                            }
                        }
                    }
                    if (nameIterator.hasNext()) {
                        builder.append('&');
                    }
                }
                byte[] bytes = builder.toString().getBytes(charset.name());
                outputMessage.getHeaders().setContentLength(bytes.length);
                StreamUtils.copy(bytes, outputMessage.getBody());

            }
        };
        converter.setCharset(Charset.forName("UTF-8"));
        List<HttpMessageConverter<?>> partConverters = new ArrayList<HttpMessageConverter<?>>();
        partConverters.add(new ByteArrayHttpMessageConverter());
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        partConverters.add(stringHttpMessageConverter);
        partConverters.add(new ResourceHttpMessageConverter());
        converter.setPartConverters(partConverters);
        return converter;
    }

    public AccountOperations accountOperations() {
        return accountOperations;
    }

    public AccountGroupOperations accountGroupOperations() {
        return accountGroupOperations;
    }

    public CampaignOperations campaignOperations() {
        return campaignOperations;
    }

    public CreativeOperations creativeOperations() {
        return creativeOperations;
    }

    public AdGroupOperations adGroupOperations() {
        return adGroupOperations;
    }

    @Override
    protected MappingJackson2HttpMessageConverter getJsonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = super
                .getJsonMessageConverter();
        objectMapper.registerModule(new FacebookAdsModule());
        converter.setObjectMapper(objectMapper);
        return converter;
    }

}
