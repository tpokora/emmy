import unittest
from server_flask.app import app
from server_flask.exercise.controllers import Exercise


class TestExerciseApi(unittest.TestCase):

    def setUp(self):
        Exercise.query.delete()
        self.test_app = app.test_client()
        self.test_exercise_name = 'Pushup'

    def test_post_create_exercise(self):
        response = self.create_exercise(self.test_exercise_name)
        self.assertEqual(response.status, '201 CREATED')
        self.assertEqual(response.json['name'], self.test_exercise_name)

    def test_get_exercises_list(self):
        self.create_exercise(self.test_exercise_name)
        response = self.test_app.get('/flask/exercise')
        self.assertEqual(response.status, '200 OK')
        self.assertIsInstance(response.json, list)
        self.assertEqual(response.json.__len__(), 1)

    """Helper methods"""
    def create_exercise(self, exercise_name):
        return self.test_app.post('/flask/exercise', data=Exercise(exercise_name).serialize())


if __name__ == '__main__':
    unittest.main()