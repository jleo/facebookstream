// Resource declarations for Resources plugin
def jqver = org.codehaus.groovy.grails.plugins.jquery.JQueryConfig.SHIPPED_VERSION

modules = {
    application {
        resource url:'js/application.js'
    }
    jquery {
        resource url:'js/jquery/jquery-1.7.1.js'
    }

}