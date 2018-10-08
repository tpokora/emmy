from flask_restful import Resource, reqparse, fields, marshal_with
from server_flask.common.errors import Error
from server_flask.common.message import Message
from flask_restful_swagger import swagger


@swagger.model
class Dog:
    def __init__(self, name, age, color):
        self.name = name
        self.age = int(age)
        self.color = color

    def serialize(self):
        return self.__dict__


dogs = [
    Dog('testDog1', 13, 'black'),
    Dog('testDog2', 10, 'brown'),
]

resource_fields = {
    'name': fields.String
}


class DogApi(Resource):
    @swagger.operation(
        notes='Get dog by name',
        nickname='get',
        responseClass=Dog.__name__,
        parameters=[
            {
                "name": "name",
                "description": "Dog name",
                "required": True,
                "allowMultiple": False,
                "dataType": 'string',
                "paramType": "path"
            }
        ]
    )
    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('name')
        args = parser.parse_args()
        name = args['name']
        for dog in dogs:
            if name == dog.name:
                return dog.serialize(), 200

        return Error('Dog not found').print(), 404

    @swagger.operation(
        notes='Add dog',
        nickname='create',
        responseClass=Dog.__name__,
        parameters=[
            {
                "name": "body",
                "description": "Dog item",
                "required": True,
                "allowMultiple": False,
                "dataType": Dog.__name__,
                "paramType": "body"
            }
        ],
        responseMesseges=[
            {
                "code": 201,
                "message": "Dog created"
            },
            {
                "code": 400,
                "message": "Dog already exists"
            }
        ]
    )
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('name')
        parser.add_argument('age')
        parser.add_argument('color')
        args = parser.parse_args()

        name = args['name']
        age = int(args['age'])
        color = args['color']

        for dog in dogs:
            if name == dog.name:
                return Error('Dog with name {} already exists'.format(name)).print(), 400

        dog = Dog(name, age, color)

        dogs.append(dog)
        return dog.serialize(), 201

    @swagger.operation(
        notes='Update Dog',
        nickname='update',
        responseClass=Dog.__name__,
        parameters=[
            {
                "name": "body",
                "description": "Dog item",
                "required": True,
                "allowMultiple": False,
                "dataType": Dog.__name__,
                "paramType": "body"
            }
        ],
        responseMesseges=[
            {
                "code": 200,
                "message": "Dog updated"
            },
            {
                "code": 201,
                "message": "Dog created"
            },
        ]
    )
    def put(self):
        parser = reqparse.RequestParser()
        parser.add_argument('name')
        parser.add_argument('age')
        parser.add_argument('color')
        args = parser.parse_args()

        name = args['name']
        age = int(args['age'])
        color = args['color']

        for dog in dogs:
            if name == dog.name:
                dog.age = age
                dog.color = color
                return dog.serialize(), 200

        dog = Dog(name, age, color)

        dogs.append(dog)
        return dog.serialize(), 201

    @swagger.operation(
        notes='Delete dog by name',
        nickname='delete',
        parameters=[
            {
                "name": "name",
                "description": "Dog name",
                "required": True,
                "allowMultiple": False,
                "dataType": 'string',
                "paramType": "path"
            }
        ]
    )
    def delete(self):
        global dogs
        parser = reqparse.RequestParser()
        parser.add_argument('name')
        args = parser.parse_args()
        name = args['name']
        dogs = [dog for dog in dogs if dog.name != name]
        return Message('{} is deleted'.format(name)).print(), 200


class DogsListApi(Resource):
    @marshal_with(resource_fields)
    @swagger.operation(
        notes='Get Dogs list',
        nickname='getDogsList',
        responseClass=Dog.__name__
    )
    def get(self):
        global dogs
        return dogs, 200
