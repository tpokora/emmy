from flask_restful import Resource, reqparse, fields, marshal_with
from server_flask.common.errors import Error
from server_flask.common.message import Message


class Dog:
    def __init__(self, name, age, color):
        self.name = name
        self.age = int(age)
        self.color = color

    def json(self):
        return self.__dict__


dogs = [
    Dog('testDog1', 13, 'black'),
    Dog('testDog2', 10, 'brown'),
]

resource_fields = {
    'name': fields.String
}


class DogApi(Resource):
    def get(self):
        parser = reqparse.RequestParser()
        parser.add_argument('name')
        args = parser.parse_args()
        for dog in dogs:
            if args['name'] == dog.name:
                return dog.json(), 200

        return Error('Dog not found').print(), 404

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
        return dog.json(), 201

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
                return dog, 200

        dog = Dog(name, age, color)

        dogs.append(dog)
        return dog.json(), 201

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
    def get(self):
        global dogs
        return dogs, 200
