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
        access_token = response.json['authentication']['access_token']
        refresh_token = response.json['authentication']['refresh_token']
        self.assertIsInstance(access_token, str)
        self.assertIsInstance(refresh_token, str)

    def test_user_logout(self):
        TestHelpers.create_user(self.test_app, self.user)
        response = TestHelpers.login_user(self.test_app, self.user)
        self.assertEqual('201 CREATED', response.status)
        access_token = response.json['authentication']['access_token']
        refresh_token = response.json['authentication']['refresh_token']
        response_logout_access = TestHelpers.logout_access_token_user(self.test_app, access_token)
        self.assertEqual('Access token has been revoked', response_logout_access.json['message'])
        response_logout_refresh = TestHelpers.logout_refresh_token_user(self.test_app, refresh_token)
        self.assertEqual('Refresh token has been revoked', response_logout_refresh.json['message'])


