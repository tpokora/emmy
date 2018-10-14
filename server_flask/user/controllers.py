from flask_restful import Resource, reqparse, fields, marshal_with
from flask_restful_swagger import swagger
from server_flask.user.models import User, db

user_list_resource_fields = {
    'id': fields.Integer,
    'username': fields.String
}

user_get_list_resources_list = {
    'id': fields.Integer,
    'username': fields.String
}

user_get_details_list_resources_list = {
    'id': fields.Integer,
    'username': fields.String,
    'password_hash': fields.String
}


class UserApi(Resource):
    @marshal_with(user_get_list_resources_list)
    @swagger.operation(
        notes='Get user by username',
        nickname='getUser',
        responseClass=User.__name__,
        parameters=[
            {
                "name": "username",
                "description": "Username",
                "required": True,
                "allowMultiple": False,
                "dataType": 'string',
                "paramType": "path"
            }
        ],
        responseMesseges=[
            {

                "code": 200,
                "message": "Returns user"
            }
        ]
    )
    def get(self, username):
        user = User.query.filter_by(username=username).first()
        if user is None:
            return user, 404
        return User.query.filter_by(username=username).first().serialize()


class UserDetailsApi(Resource):
    @marshal_with(user_get_details_list_resources_list)
    @swagger.operation(
        notes='Get user details by username',
        nickname='getUserDetails',
        responseClass=User.__name__,
        parameters=[
            {
                "name": "username",
                "description": "Username",
                "required": True,
                "allowMultiple": False,
                "dataType": 'string',
                "paramType": "path"
            }
        ],
        responseMesseges=[
            {

                "code": 200,
                "message": "Returns user details"
            }
        ]
    )
    def get(self, username):
        user = User.query.filter_by(username=username).first()
        if user is None:
            return user, 404
        return User.query.filter_by(username=username).first().serialize()


class UserListApi(Resource):
    @marshal_with(user_list_resource_fields)
    @swagger.operation(
        notes='Get all users',
        nickname='getUsersList',
    )
    def get(self):
        return [user.serialize() for user in User.query.all()], 200

    @swagger.operation(
        tags=['user'],
        notes='Create user',
        nickname='createUser',
        responseClass=User.__name__,
        parameters=[
            {
                "name": "body",
                "description": "User item",
                "required": True,
                "allowMultiple": False,
                "dataType": User.__name__,
                "paramType": "body"
            }
        ],
        responseMesseges=[
            {

                "code": 201,
                "message": "User created"
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
        user = User(username)
        user.hash_password(password)

        db.session.add(user)
        db.session.commit()

        return user.serialize(), 201

