class NcsWebTemplateGrailsPlugin {
    // the plugin version
    def version = "0.2.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp",
			"grails-app/conf/*",
			"lib/"
    ]

    def author = "Aaron J. Zirbes"
    def authorEmail = "ajz@umn.edu"
    def title = "NCS Sitemesh Template for Grails"
    def description = '''\\
This provides a U of MN Branded NCS Sitemesh template for Grails
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/ncs-web-template"

}
