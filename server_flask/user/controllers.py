from flask_restful import Resource, reqparse
from flask_restful_swagger import swagger
from server_flask.user.models import User


class UserListApi(Resource):
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
        parser.add_argument('name')
        parser.add_argument('password')
        args = parser.parse_args()
        name = args['name']
        password = args['password']
        user = User(name)
        user.hash_password(password)

        return user.serialize(), 201

