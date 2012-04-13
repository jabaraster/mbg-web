package jabara.web.page;

import jabara.web.WicketApplication;
import jabara.web.page.MaintePage;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage {
    private WicketTester tester;

    @Before
    public void setUp() {
        this.tester = new WicketTester(new WicketApplication());
    }

    @Test
    public void homepageRendersSuccessfully() {
        // start and render the test page
        this.tester.startPage(MaintePage.class);

        // assert rendered page class
        this.tester.assertRenderedPage(MaintePage.class);
    }
}
