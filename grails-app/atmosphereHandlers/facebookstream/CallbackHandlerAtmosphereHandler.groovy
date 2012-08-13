package facebookstream

import org.atmosphere.cpr.*

public class CallbackHandlerAtmosphereHandler implements AtmosphereHandler {
    @Override
    void onRequest(AtmosphereResource event) {
        def request = event.request
        def response = event.response
        println "on request"
        event.suspend()

        Broadcaster b = BroadcasterFactory.getDefault()
                .lookup("onUpdate",true);
        b.addAtmosphereResource(event)
//        event.broadcaster = b
//        def broadcaster = event.broadcaster
//        request.session.broadcaster = ['/atmosphere/callback':broadcaster]
    }

    @Override
    void onStateChange(AtmosphereResourceEvent event) {
        AtmosphereResponse res = event.resource.response

        if (event.message) {
            println "state changed"
            if (event.isSuspended()) {
                String body = event.getMessage().toString();
                if(body){
                    println "body:"+body
                    res.getWriter().write(body);
                }

                event.resource.resume()
            }
        }
    }

    @Override
    void destroy() {
    }
}
