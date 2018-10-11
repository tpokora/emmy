import os
from flask import Flask, redirect
from flask_restful import Api, Resource
from server_flask.dog.controllers import DogApi, DogsListApi
from flask_restful_swagger import swagger


app = Flask(__name__)
app.config.from_object(os.environ['APP_SETTINGS'])
api = swagger.docs(Api(app),
                   apiVersion='0.1',
                   basePath='http://localhost:5000',
                   resourcePath='/',
                   produces=["application/json", "text/html"],
                   api_spec_url='/api/spec',
                   description='A Simple Emmy API')


class Hello(Resource):
    """My Hello API"""
    @swagger.operation(
        notes='Get Hello Page',
        nickname='get'
    )
    def get(self):
        return {'message': 'Hello'}


@app.route('/')
def swagger():
    return redirect('api/spec.html', code=302)


api.add_resource(Hello, '/flask/hello')
api.add_resource(DogApi, '/flask/dog/<string:name>')
api.add_resource(DogsListApi, '/flask/dog')

if __name__ == "__main__":
    app.run(debug=True)
