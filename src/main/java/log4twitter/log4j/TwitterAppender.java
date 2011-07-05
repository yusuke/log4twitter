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

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import log4twitter.TwitterWrapper;

/**
 * A log4j appender for twitter
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Log4Twitter 1.0.0
 */
public final class TwitterAppender extends AppenderSkeleton {
    public TwitterAppender() {
        super();
    }

    /**
     * append
     *
     * @param eventObject LoggingEvent
     */
    protected void append(LoggingEvent eventObject) {
        twitter.sendMessage(this.getLayout().format(eventObject));
    }

    private TwitterWrapper twitter = new TwitterWrapper();

    public void setAsync(String async) {
        twitter.setAsync(async);
    }

    public void setInclude(String include) {
        twitter.setInclude(include);
    }

    public void setExclude(String exclude) {
        twitter.setExclude(exclude);
    }

    public void setSubscribers(String subscribers) {
        twitter.setSubscribers(subscribers);
    }

    /**
     * close
     */
    public void close() {
        //nothing to do
    }

    /**
     * requiresLayout
     *
     * @return boolean
     */
    public boolean requiresLayout() {
        return false;
    }
}
