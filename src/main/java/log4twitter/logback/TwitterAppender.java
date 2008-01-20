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
