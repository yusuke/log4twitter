/*
 * Copyright 2007 Yusuke Yamamoto
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
package log4twitter.log4j;

import org.apache.log4j.Logger;
import junit.framework.TestCase;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import java.util.Date;

/**
 */
public class TestLog4jTwitterAppender extends TestCase {

    public TestLog4jTwitterAppender(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testLog4J() throws Exception{
        Logger logger = Logger.getLogger(TestLog4jTwitterAppender.class);
        String str = "debug:"+new Date();
        logger.debug(str);

        Twitter twitter = new TwitterFactory("/log4twitter").getInstance();
        Status status = twitter.getUserTimeline("twit4j").get(0);
        assertTrue(status.getText().contains(str));
    }

}
