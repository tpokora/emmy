import os
import unittest
from server_flask.app import app
from server_flask.common.models import db
from server_flask.tests.test_helpers import TestHelpers
from server_flask.user.models import User


class LoginApiTest(unittest.TestCase):
    def setUp(self):
        self.test_app = app.test_client()
        self.test_app.application.config.from_object(os.environ['APP_SETTINGS'])
        db.app = self.test_app.application
        db.drop_all()
        db.create_all()
        self.user = User()
        self.user.username = 'testUser'
        self.user.password = 'testPassword'

    def test_user_login(self):
        TestHelpers.create_user(self.test_app, self.user)
        response = TestHelpers.login_user(self.test_app, self.user)
        self.assertEqual('201 CREATED', response.status)
        self.assertIsInstance(response.json['access_token'], str)
        self.assertIsInstance(response.json['refresh_token'], str)

