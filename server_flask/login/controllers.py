from flask_restful import Resource, reqparse, fields, marshal_with
from flask_jwt_extended import (create_access_token, create_refresh_token, jwt_required, jwt_refresh_token_required, get_jwt_identity, get_raw_jwt)
from flask_restful_swagger import swagger

from server_flask.common.errors import Error
from server_flask.common.models import Message
from server_flask.login.models import RevokedToken, Authentication
from server_flask.user.models import User


class LoginApi(Resource):
    @swagger.operation(
        notes='Login user',
        nickname='login',
        responseClass=Authentication.__name__,
        parameters=[
            {
                'name': 'username',
                'description': 'Username',
                'required': True,
                'allowMultiple': False,
                'dataType': 'string',
                'paramType': 'path'
            },
            {
                'name': 'password',
                'description': 'User password',
                'required': True,
                'allowMultiple': False,
                'dataType': 'string',
                'paramType': 'path'
            }
        ],
        responseMesseges=[
            {

                'code': 201,
                'message': 'Returns authentication and refresh token'
            }
        ]
    )
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('username')
        parser.add_argument('password')
        args = parser.parse_args()
        username = args['username']
        password = args['password']
        user = User.query.filter_by(username=username).first()

        if not user:
            return Message('User {} doesn\'t exist'.format(username)).print(), 404

        if User.verify_hash(password, user.password):
            access_token = create_access_token(identity=username)
            refresh_token = create_refresh_token(identity=username)
            return {
                       'message': 'User {} logged in'.format(username),
                       'access_token': access_token,
                       'refresh_token': refresh_token
                    }, 201
        else:
            return Error('Could not log in'), 404


class UserLogoutAccess(Resource):
    @swagger.operation(
        notes='Logout user',
        nickname='logout',
        responseMesseges=[
            {

                'code': 201,
                'message': 'Revokes access token'
            }
        ]
    )
    @jwt_required
    def post(self):
        jti = get_raw_jwt()['jti']
        try:
            revoked_token = RevokedToken(jti=jti)
            revoked_token.add()
            return Message('Access token has been revoked').print(), 200
        except:
            return Error('Something went wrong').print(), 500


class UserLogoutRefresh(Resource):
    @swagger.operation(
        notes='Logout user',
        nickname='logout',
        responseMesseges=[
            {

                'code': 201,
                'message': 'Revokes refresh token'
            }
        ]
    )
    @jwt_refresh_token_required
    def post(self):
        jti = get_raw_jwt()['jti']
        try:
            revoked_token = RevokedToken(jti=jti)
            revoked_token.add()
            return Message('Refresh token has been revoked').print(), 200
        except:
            return Error('Something went wrong').print(), 500
