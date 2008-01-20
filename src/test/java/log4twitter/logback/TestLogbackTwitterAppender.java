package log4twitter.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import junit.framework.TestCase;

/**
 */
public class TestLogbackTwitterAppender extends TestCase {

    public TestLogbackTwitterAppender(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    final static Logger logger = LoggerFactory.getLogger(TestLogbackTwitterAppender.class);
    public void testCloseWriter()throws Exception {
        logger.info("Entering application.");


        logger.info("Exiting application.");
        Thread.sleep(10000);
    }

}
