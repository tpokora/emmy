import unittest
from server_flask.app import app
from server_flask.resources.dog import Dog


class TestDogApi(unittest.TestCase):

    def setUp(self):
        self.test_app = app.test_client()
        self.test_dog1_name = 'testDog1'
        self.test_dog2_name = 'testDog2'

    def test_getDogByName(self):
        response = self.get_dog_by_name_response(self.test_dog1_name)
        self.assertEqual(response.status, '200 OK')
        self.assertEqual(response.json['name'], self.test_dog1_name)

    def test_createDog(self):
        dog_name = 'newTestDog'
        new_dog = Dog(dog_name, 10, 'red')
        response = self.test_app.post('/flask/dog', data=new_dog.serialize())
        self.assertEqual(response.status, '201 CREATED')
        self.assertEqual(response.json['name'], dog_name)

    def test_updateDog(self):
        response = self.get_dog_by_name_response(self.test_dog1_name)
        old_age = response.json['age']
        old_color = response.json['color']

        new_age = 11
        new_color = 'updated_color'
        update_dog = Dog(self.test_dog1_name, new_age, new_color)
        response = self.test_app.put('/flask/dog/{}'.format(self.test_dog1_name), data=update_dog.serialize())
        self.assertEqual(response.status, '200 OK')
        self.assertEqual(response.json['name'], self.test_dog1_name)
        self.assertEqual(response.json['age'], new_age)
        self.assertEqual(response.json['color'], new_color)

        updated_response = self.get_dog_by_name_response(self.test_dog1_name)
        self.assertEqual(updated_response.status, '200 OK')
        self.assertEqual(updated_response.json['name'], self.test_dog1_name)
        self.assertEqual(updated_response.json['age'], new_age)
        self.assertNotEqual(updated_response.json['age'], old_age)
        self.assertEqual(updated_response.json['color'], new_color)
        self.assertNotEqual(updated_response.json['color'], old_color)

    def test_deleteDogByName(self):
        response = self.test_app.delete('/flask/dog/{}'.format(self.test_dog2_name))
        self.assertEqual(response.status, '200 OK')
        self.assertEqual(response.json['message'], '{} is deleted'.format(self.test_dog2_name))

    def test_getDogsList(self):
        response = self.test_app.get('/flask/dog')
        self.assertEqual(response.status, '200 OK')
        self.assertIsInstance(response.json, list)

    '''Helper methods'''
    def get_dog_by_name_response(self, dog_name):
        return self.test_app.get('/flask/dog/{}'.format(dog_name))


if __name__ == '__main__':
    unittest.main()
