import os
from flask import Flask, redirect
from flask_cors import CORS
from flask_restful import Api, Resource
from flask_restful_swagger import swagger
from flask_jwt_extended import JWTManager

from server_flask.common.models import db, Message
from server_flask.exercise.controllers import ExerciseListApi
from server_flask.login.controllers import LoginApi, UserLogoutAccess, UserLogoutRefresh
from server_flask.login.models import RevokedToken
from server_flask.user.controllers import UserListApi, UserApi, UserDetailsApi


app = Flask(__name__)
app.config.from_object(os.environ['APP_SETTINGS'])
app.config['JWT_SECRET_KEY'] = 'jwt-secret-string'
app.config['JWT_BLACKLIST_ENABLED'] = True
app.config['JWT_BLACKLIST_TOKEN_CHECKS'] = ['access', 'refresh']
CORS(app)
jwt = JWTManager(app)
with app.app_context():
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
        return Message('Hello to Emmy!').serialize()


@app.route('/')
def swagger():
    return redirect('api/spec.html', code=302)


@jwt.token_in_blacklist_loader
def check_if_token_in_blacklist(decrypted_token):
    jti = decrypted_token['jti']
    return RevokedToken.is_jti_blacklisted(jti)


api.add_resource(Hello, '/flask/hello')
api.add_resource(UserListApi, '/flask/user')
api.add_resource(UserApi, '/flask/user/<string:username>')
api.add_resource(UserDetailsApi, '/flask/user/<string:username>/details')
api.add_resource(LoginApi, '/flask/login')
api.add_resource(UserLogoutAccess, '/flask/logout/access')
api.add_resource(UserLogoutRefresh, '/flask/logout/refresh')
api.add_resource(ExerciseListApi, '/flask/exercise')

if __name__ == "__main__":
    app.run()
