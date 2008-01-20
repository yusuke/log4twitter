package log4twitter.log4j;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import log4twitter.TwitterWrapper;

/**
 * A log4j appender for twitter
 */
public class TwitterAppender extends AppenderSkeleton {
  public TwitterAppender() {
    super();
  }

  /**
   * append
   *
   * @param loggingEvent LoggingEvent
   */
  protected void append(LoggingEvent eventObject) {
      twitter.sendMessage(this.getLayout().format(eventObject));
  }
  TwitterWrapper twitter = new TwitterWrapper();
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
  /**
   * close
   *
   */
  public void close() {
      //nothingo to do
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
