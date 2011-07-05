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
package log4twitter;

import twitter4j.AsyncTwitter;
import twitter4j.AsyncTwitterFactory;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

import java.util.regex.Pattern;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @version 1.0.2
 * @since Log4Twitter 1.0.0
 */
final public class TwitterWrapper {
    static{
        System.setProperty("twitter4j.loggerFactory", "twitter4j.internal.logging.NullLoggerFactory");
        
    }
    public TwitterWrapper() {
        super();
        initTwitter();
    }

    private Pattern include = null;
    private Pattern exclude = null;
    private boolean async = true;
    private Twitter twitter = null;
    private AsyncTwitter asyncTwitter = null;

    private String[] subscribers = null;

    public void sendMessage(String msg) {
        msg = msg.trim();
        //apply include filter
        if (include == null || include.matcher(msg).matches()) {
            //apply exclude filter
            if (exclude == null || !exclude.matcher(msg).matches()) {
                send(msg);
            }
        }
    }

    private void send(String msg) {
        try {
            if (async) {
                asyncTwitter.updateStatus(msg);
            } else {
                twitter.updateStatus(msg);
            }
        } catch (TwitterException te) {
            te.printStackTrace();
        }
        // send direct messages
        if (null != subscribers) {
            for (int i = 0; i < subscribers.length; i++) {
                try {
                    if (async) {
                        asyncTwitter.sendDirectMessage(subscribers[i], msg);
                    } else {
                        twitter.sendDirectMessage(subscribers[i], msg);
                    }
                } catch (TwitterException te) {
                    te.printStackTrace();
                }
            }
        }
    }

    private void initTwitter() {
        twitter = null;
        asyncTwitter = null;
        if (this.async) {
            asyncTwitter = new AsyncTwitterFactory("/log4twitter").getInstance();
        } else {
            twitter = new TwitterFactory("/log4twitter").getInstance();
        }
    }

    public void setAsync(String async) {
        this.async = "true".equalsIgnoreCase(async) || "yes".equalsIgnoreCase(async);
        initTwitter();
    }

    public void setInclude(String include) {
        this.include = Pattern.compile(include);
    }

    public void setExclude(String exclude) {
        this.exclude = Pattern.compile(exclude);
    }

    public void setSubscribers(String subscribers) {
        this.subscribers = subscribers.split(",");
    }
}
