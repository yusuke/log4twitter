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

import java.util.regex.Pattern;

import twitter4j.AsyncTwitter;
import twitter4j.TwitterException;

/**
 * <p>Title: Log4twitter</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007 Yusuke Yamamoto</p>
 *
 * <p> </p>
 *
 * @author Yusuke Yamamoto
 * @version 1.0.0
 */
public class TwitterWrapper {
    public TwitterWrapper() {
        super();
    }

    private String id = null;
    private String password = null;
    private Pattern include = null;
    private Pattern exclude = null;
    private boolean async = true;
    private int retryCount = 0;
    private int retryIntervalSecs = 10;
    private AsyncTwitter twitter = null;

    private String[] subscribers = null;

    public void sendMessage(String msg) {
        msg = msg.trim();
        //apply include filter
        if (null == include || include.matcher(msg).matches()) {
            //apply exclude filter
            if (null == exclude || !exclude.matcher(msg).matches()) {
                send(msg);
            }
        }
    }
    private void send(String msg) {
        if (null == twitter) {
            System.out.print("Warning: Twitter id/password combination is not properly set." + msg);
        } else {
            try {
                if (async) {
                    twitter.updateAsync(msg);
                } else {
                    twitter.update(msg);
                }
            } catch (TwitterException ignore) {
            }
            // send direct messages
            if (null != subscribers) {
                for (int i = 0; i < subscribers.length; i++) {
                    try {
                        if (async) {
                            twitter.sendDirectMessageAsync(subscribers[i], msg);
                        } else {
                            twitter.sendDirectMessage(subscribers[i], msg);
                        }
                    } catch (TwitterException ignore) {
                    }
                }
            }
        }
    }

    public void setId(String id) {
        this.id = id;
        initTwiter();
    }

    public void setPassword(String password) {
        this.password = password;
        initTwiter();
    }

    private void initTwiter() {
        if (null != id && null != password) {
            twitter = new AsyncTwitter(id, password);
            twitter.setRetryCount(retryCount);
            twitter.setRetryIntervalSecs(retryIntervalSecs);
        }
    }

    public void setRetryCount(int retryCount) {
        if (retryCount > 0) {
            this.retryCount = retryCount;
        }
        if (null != twitter) {
            twitter.setRetryIntervalSecs(this.retryCount);
        }
    }

    public void setRetryIntervalSecs(int retryIntervalSecs) {
        if (retryIntervalSecs > 0) {
            this.retryIntervalSecs = retryIntervalSecs;
        }
        if (null != twitter) {
            twitter.setRetryIntervalSecs(this.retryIntervalSecs);
        }

    }

    public void setAsync(String async) {
        this.async = "true".equalsIgnoreCase(async) || "yes".equalsIgnoreCase(async);
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
