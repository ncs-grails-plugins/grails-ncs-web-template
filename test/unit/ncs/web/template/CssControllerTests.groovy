package ncs.web.template

import grails.test.*

class CssControllerTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testTemplate() {
		def cc = new CssController()
		assert cc.template
    }

    void testPrint() {
		def cc = new CssController()
		assert cc.print
    }
}
