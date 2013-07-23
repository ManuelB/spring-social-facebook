package org.springframework.social.facebook.api.ads;

import java.util.List;

import org.springframework.social.facebook.api.Identifier;

public interface SearchOperations {
	public Identifier getIdByUrl(String url);

	public List<Identifier> getKeywordAutocomplete(String keyword);

	public List<Identifier> getKeywordSuggestions(String... keyword);

	public List<ValidKeyword> getValidKeywords(String... keywords);

}
