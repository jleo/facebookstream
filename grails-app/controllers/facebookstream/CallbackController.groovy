package facebookstream

class CallbackController {

    def index() {
    }

    def challenge = {
        def verifyToken = grailsApplication.config.facebook.VERIFY_TOKEN

        if (params.token == verifyToken) {
            render params."hub.challenge"
        }
    }

    def onUpdate() {
//        def sample = """{
//"object": "user",
//"entry":
//[
//    {
//        "uid": 1335845740,
//        "changed_fields":
//        [
//            "name",
//            "picture"
//        ],
//       "time": 232323
//    },
//    {
//        "uid": 1234,
//        "changed_fields":
//        [
//            "friends"
//        ],
//       "time": 232325
//    }
//]
//}"""
        request.withFormat {
            json {

            }
        }
    }

    def addsubscription() {

        def appId = grailsApplication.config.facebook.APP_ID
        def tempToken = grailsApplication.config.facebook.TEMP_TOKEN
        def callbackURL = grailsApplication.config.facebook.CALLBACK
        def verifyToken = grailsApplication.config.facebook.VERIFY_TOKEN

        def token = URLEncoder.encode("${appId}|${tempToken}", "utf-8")
        def url = "https://graph.facebook.com/${appId}/subscriptions?access_token=${token}"
        println url
        def post = "object=user&callback_url=${URLEncoder.encode("${callbackURL}", "utf-8")}&fields=${URLEncoder.encode("activities,events,feed,friends,name,picture", "utf-8")}&verify_token=${verifyToken}"//this is your call, it is for sake of security,you need to check if the token is yours

        def addSubURL = new URL(url)
        def connection = addSubURL.openConnection()
        ((HttpURLConnection) connection).setRequestMethod("POST");
        connection.doOutput = true
        connection.doInput = true

        Writer writer = new OutputStreamWriter(connection.outputStream)
        writer.write(post)

        writer.flush()
        writer.close()
        if (connection.responseCode == 200)
            render "succeeded, " + connection.inputStream.text
        else
            render connection.responseCode
    }
}
