from flask_restful import Resource, reqparse
from .errors import Error

dogs = [
    {
        "name": "Goran",
        "age": 13,
        "color": "black"
    },
    {
        "name": "Dora",
        "age": 10,
        "color": "brown"
    }
]


class DogApi(Resource):
    def get(self, name):
        for dog in dogs:
            if name == dog["name"]:
                return dog, 200

        return Error("Dog not found").print(), 404

    def post(self, name):
        parser = reqparse.RequestParser()
        parser.add_argument("age")
        parser.add_argument("color")
        args = parser.parse_args()

        for dog in dogs:
            if name == dog["name"]:
                return Error("Dog with name {} already exists".format(name)).print(), 400

        dog = {
            "name": name,
            "age": int(args["age"]),
            "color": args["color"]
        }

        dogs.append(dog)
        return dog, 201

    def put(self, name):
        parser = reqparse.RequestParser()
        parser.add_argument("age")
        parser.add_argument("color")
        args = parser.parse_args()

        for dog in dogs:
            if name == dog["name"]:
                dog["age"] = args["age"]
                dog["color"] = args["color"]
                return dog, 200

        dog = {
            "name": name,
            "age": args["age"],
            "color": args["color"]
        }

        dogs.append(dog)
        return dog, 201

    def delete(self, name):
        global dogs
        dogs = [dog for dog in dogs if dog["name"] != name]
        return Error("{} is deleted".format(name)).print(), 200


class DogsListApi(Resource):
    def get(self):
        return dogs, 200
