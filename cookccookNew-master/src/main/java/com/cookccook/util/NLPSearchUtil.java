package com.cookccook.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.KomoranResult;
import kr.co.shineware.nlp.komoran.model.Token;

@Component
public class NLPSearchUtil {

	public List<String> nlpSearchResult(String sentence) {
		List<String> resultList = new ArrayList<>();
		Komoran komoran = new Komoran(DEFAULT_MODEL.LIGHT);
		KomoranResult analyseResultList = komoran.analyze(sentence);
		List<Token> tokenList = analyseResultList.getTokenList();
		String resultWord = null;
		for (Token token: tokenList) {
			resultList = analyseResultList.getMorphesByTags("XR", "NNG");
		}
		return resultList;
	}
	
	
	
	
}
