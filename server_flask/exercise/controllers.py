from flask_restful import Resource, reqparse
from flask_restful_swagger import swagger
from .models import Exercise, db


class ExerciseListApi(Resource):
    @swagger.operation(
        notes='Get all exercises',
        nickname='getExercisesList',
    )
    def get(self):
        return [exercise.serialize() for exercise in Exercise.query.all()], 200

    @swagger.operation(
        notes='Create exercise',
        nickname='createExercise',
        responseClass=Exercise.__name__,
        parameters=[
            {
                "name": "body",
                "description": "Exercise item",
                "required": True,
                "allowMultiple": False,
                "dataType": Exercise.__name__,
                "paramType": "body"
            }
        ],
        responseMesseges=[
            {

                "code": 201,
                "message": "Exercise created"
            }
        ]
    )
    def post(self):
        parser = reqparse.RequestParser()
        parser.add_argument('name')
        args = parser.parse_args()
        name = args['name']
        exercise = Exercise(name)

        db.session.add(exercise)
        db.session.commit()

        return Exercise.query.filter(Exercise.name == name).first().serialize(), 201
