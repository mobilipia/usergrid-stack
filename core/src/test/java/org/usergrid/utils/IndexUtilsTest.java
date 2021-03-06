/*******************************************************************************
 * Copyright 2012 Apigee Corporation
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.usergrid.utils;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.usergrid.persistence.entities.Activity;
import org.usergrid.persistence.entities.User;

public class IndexUtilsTest {

	private static final Logger logger = LoggerFactory.getLogger(IndexUtilsTest.class);

	@Test
	public void testKeywords() {

		String test = "Dragons, the policeman knew, were supposed to breathe fire and occasionally get themselves slaughtered.";
		List<String> keywords = IndexUtils.keywords(test);

		assertEquals(11, keywords.size());

		for (String keyword : keywords) {
			logger.info(keyword);
		}
	}

	@Test
	public void testKeyValue() throws Exception {

		User user = new User();
		user.setUsername("edanuff");
		user.setEmail("ed@anuff.com");

		Activity activity = Activity.newActivity(Activity.VERB_POST, null,
				"I ate another sammich", null, user, null, "tweet", null, null);

		List<Entry<String, Object>> l = IndexUtils.getKeyValueList(activity,
				false);
		for (Entry<String, Object> e : l) {
			logger.info(e.getKey() + " = " + e.getValue());
		}

		assertEquals(7, l.size());

	}
}
