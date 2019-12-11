import tornado.web
import tornado

import tornado.ioloop
import tornado.web


class MainHandler(tornado.web.RequestHandler):
    def get(self):
        self.render("./index.html")

settings = {
    "static_path": "./static",
}

if __name__ == "__main__":
    application = tornado.web.Application([
        (r"/", MainHandler),
    ],
    **settings
    )
    application.listen(8888)
    tornado.ioloop.IOLoop.current().start()
