from flask_restful import Resource, reqparse, fields, marshal_with
from flask_restful_swagger import swagger
from flask_jwt_extended import (create_access_token, create_refresh_token, jwt_required, jwt_refresh_token_required, get_jwt_identity, get_raw_jwt)
from server_flask.user.models import User, UserResourcesList, db


class UserApi(Resource):
    @marshal_with(UserResourcesList.user_get)
    @swagger.operation(
        notes='Get user by username',
        nickname='getUser',
        responseClass=User.__name__,
        parameters=[
            {
                'name': 'username',
                'description': 'Username',
                'required': True,
                'allowMultiple': False,
                'dataType': 'string',
                'paramType': 'path'
            }
        ],
        responseMesseges=[
            {

                'code': 200,
                'message': 'Returns user'
            }
        ]
    )
    def get(self, username):
        user = User.query.filter_by(username=username).first()
        if user is None:
            return user, 404
        return User.query.filter_by(username=username).first().serialize()


class UserDetailsApi(Resource):
    @swagger.operation(
        notes='Get user details by username',
        nickname='getUserDetails',
        responseClass=User.__name__,
        parameters=[
            {
                'name': 'username',
                'description': 'Username',
                'required': True,
                'allowMultiple': False,
                'dataType': 'string',
                'paramType': 'path'
            }
        ],
        responseMesseges=[
            {

                'code': 200,
                'message': 'Returns user details'
            }
        ]
    )
    @jwt_required
    def get(self, username):
        user = User.query.filter_by(username=username).first()
        if user is None:
            return user, 404
        return User.query.filter_by(username=username).first().serialize()


class UserListApi(Resource):
    @marshal_with(UserResourcesList.user_list)
    @swagger.operation(
        notes='Get all users',
        nickname='getUsersList',
    )
    def get(self):
        return [user.serialize() for user in User.query.all()], 200

    @swagger.operation(
        notes='Create user',
        nickname='createUser',
        responseClass=User.__name__,
        parameters=[
            {
                'name': 'body',
                'description': 'User item',
                'required': True,
                'allowMultiple': False,
                'dataType': User.__name__,
                'paramType': 'body'
            }
        ],
        responseMesseges=[
            {

                'code': 201,
                'message': 'User created'
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
        user_exists = User.query.filter_by(username=username).first()
        if user_exists:
            return {'message': 'User {} already exists'.format(username)}, 422

        user = User()
        user.username = username
        user.password = User.generate_hash(password)
        user.active = True

        try:
            db.session.add(user)
            db.session.commit()
            access_token = create_access_token(identity=username)
            refresh_token = create_refresh_token(identity=username)
            return {
                'message': 'User created',
                'user': user.serialize(),
                'access_token': access_token,
                'refresh_token': refresh_token
                }, 201
        except:
            return {'message': 'Something went wrong'}, 500


    def delete(self):
        db.session.query(User).delete()
        db.session.commit()
        return [user.serialize() for user in User.query.all()], 200

