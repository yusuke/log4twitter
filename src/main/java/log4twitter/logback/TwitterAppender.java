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
package log4twitter.logback;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.Layout;
import log4twitter.TwitterWrapper;
/**
 * A logback appender for twitter
 */
public class TwitterAppender<E> extends AppenderBase<E> {
  public TwitterAppender() {
    super();
  }

  /**
   * append
   *
   * @param eventObject Object
   */
  protected void append(E eventObject) {
    if (!isStarted()) {
      return;
    }
    twitter.sendMessage(this.getLayout().doLayout(eventObject));
  }

  private TwitterWrapper twitter = new TwitterWrapper();
  public void setId(String id){
      twitter.setId(id);
  }
  public void setPassword(String password){
      twitter.setPassword(password);
  }
  public void setAsync(String async){
      twitter.setAsync(async);
  }
  public void setInclude(String include){
      twitter.setInclude(include);
  }
  public void setExclude(String exclude){
      twitter.setExclude(exclude);
  }
  public void setSubscribers(String subscribers){
      twitter.setSubscribers(subscribers);
  }
  public void setRetryCount(int count){
      twitter.setRetryCount(count);
  }
  public void setRetryIntervalSecs(int seconds){
      twitter.setRetryIntervalSecs(seconds);
  }


  Layout<E> layout;
  public void setLayout(Layout<E> layout) {
    this.layout = layout;
  }

  public Layout<E> getLayout() {
    return layout;
  }
}
