package log4twitter.log4j;

import org.apache.log4j.Logger;
import junit.framework.TestCase;

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
        logger.debug("debug");
    }

}
