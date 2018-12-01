import os
import unittest
from server_flask.app import app
from server_flask.common.models import db
from server_flask.user.models import User


class UserApiTest(unittest.TestCase):
    def setUp(self):
        self.test_app = app.test_client()
        self.test_app.application.config.from_object(os.environ['APP_SETTINGS'])
        db.app = self.test_app.application
        db.drop_all()
        db.create_all()
        self.user = User()
        self.user.username = 'testUser'
        self.user.password = 'testPassword'

    def test_user___repr__(self):
        self.assertEqual(self.user.__repr__(), '<User {}, {}>'.format(self.user.username, self.user.password))

    def test_user_verify_hash(self):
        user_password_hash = User.generate_hash(self.user.password)
        self.assertTrue(User.verify_hash(self.user.password, user_password_hash))

    def test_create_user(self):
        response = self.create_user()
        self.assertEqual(response.status, '201 CREATED')
        self.assertIsInstance(response.json['id'], int)
        self.assertEqual(response.json['username'], self.user.username)

    def test_user_api_empty_list(self):
        response = self.test_app.get('/flask/user')
        self.assertEqual(response.status, '200 OK')
        self.assertIsInstance(response.json, list)
        self.assertEqual(response.json.__len__(), 0)

    def test_user_api_create_user(self):
        self.create_user()
        response = self.test_app.get('/flask/user')
        self.assertEqual(response.status, '200 OK')
        self.assertIsInstance(response.json, list)
        self.assertEqual(response.json.__len__(), 1)
        self.assertIsInstance(response.json[0]['id'], int)
        self.assertIsInstance(response.json[0]['username'], str)

    def test_user_api_get_user_by_username(self):
        self.create_user()
        response = self.test_app.get('/flask/user/' + self.user.username)
        self.assertEqual(response.status, '200 OK')
        self.assertIsInstance(response.json['id'], int)
        self.assertEqual(response.json['username'], self.user.username)

    def test_user_api_get_user_by_username_notfound(self):
        response = self.test_app.get('/flask/user/notfoundusername')
        self.assertEqual(response.status, '404 NOT FOUND')

    def test_user_api_get_user_details_by_username(self):
        self.create_user()
        response = self.test_app.get('/flask/user/' + self.user.username + '/details')
        self.assertEqual(response.status, '200 OK')
        self.assertIsInstance(response.json['id'], int)
        self.assertEqual(response.json['username'], self.user.username)
        self.assertTrue(User.verify_hash(self.user.password, response.json['password']))

    def test_user_api_get_user_details_by_username_notfound(self):
        response = self.test_app.get('/flask/user/notfoundusername/details')
        self.assertEqual(response.status, '404 NOT FOUND')

    '''Helper Methods'''
    def create_user(self):
        return self.test_app.post('/flask/user', data={'username': self.user.username, 'password': self.user.password})
