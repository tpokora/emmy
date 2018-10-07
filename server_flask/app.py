from flask import Flask
from flask_restful import Api
from server_flask.resources.dog import DogApi, DogsListApi


app = Flask(__name__)
api = Api(app)
api.add_resource(DogApi, "/flask/dog")
api.add_resource(DogsListApi, "/flask/dogs")
app.run(debug=True)
