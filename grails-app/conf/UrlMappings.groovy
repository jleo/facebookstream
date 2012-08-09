class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

        "/callback"(controller:"callback") {
            action = [GET:"challenge", POST:"onUpdate"]
        }

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
