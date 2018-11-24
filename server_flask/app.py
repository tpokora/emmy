import os
from flask import Flask, redirect
from flask_cors import CORS
from flask_restful import Api, Resource
from flask_restful_swagger import swagger
from flask_security import SQLAlchemyUserDatastore, Security, auth_token_required
from server_flask.common.models import db
from server_flask.dog.controllers import DogApi, DogsListApi
from server_flask.exercise.controllers import ExerciseListApi
from server_flask.user.controllers import UserListApi, UserApi, UserDetailsApi
from server_flask.user.models import User, Role

app = Flask(__name__)
CORS(app)
app.config.from_object(os.environ['APP_SETTINGS'])
with app.app_context():
    user_datastore = SQLAlchemyUserDatastore(db, User, Role)
    security = Security(app, user_datastore)
    db.init_app(app)
    db.create_all()
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
        return {'message': 'Hello to Emmy!'}


@app.route('/')
def swagger():
    return redirect('api/spec.html', code=302)


api.add_resource(Hello, '/flask/hello')
api.add_resource(DogsListApi, '/flask/dog')
api.add_resource(DogApi, '/flask/dog/<string:name>')
api.add_resource(ExerciseListApi, '/flask/exercise')
api.add_resource(UserListApi, '/flask/user')
api.add_resource(UserApi, '/flask/user/<string:username>')
api.add_resource(UserDetailsApi, '/flask/user/<string:username>/details')

if __name__ == "__main__":
    app.run()
