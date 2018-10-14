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
        self.user = User('testUser')
        self.password = 'testPassword'
        self.user.hash_password(self.password)

    def test_user___repr__(self):
        self.assertEqual(self.user.__repr__(), '<User {}, {}>'.format(self.user.name, self.user.password_hash))

    def test_user_password_verify(self):
        self.assertEqual(self.user.verify_password(self.password), True)

    def test_create_user(self):
        response = self.test_app.post('/flask/user', data={"name": self.user.name, "password": self.password})
        self.assertEqual(response.status, '201 CREATED')
        self.assertEqual(response.json['name'], self.user.name)
