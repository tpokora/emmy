"""Helper Methods"""


class TestHelpers:
    @staticmethod
    def create_user(test_app, user):
        return test_app.post('/flask/user', data={'username': user.username, 'password': user.password})

    @staticmethod
    def login_user(test_app, user):
        return test_app.post('/flask/login', data={'username': user.username, 'password': user.password})

    @staticmethod
    def logout_access_token_user(test_app, access_token):
        return test_app.post('/flask/logout/access', headers={'Authorization': 'Bearer ' + access_token})

    @staticmethod
    def logout_refresh_token_user(test_app, refresh_token):
        return test_app.post('/flask/logout/refresh', headers={'Authorization': 'Bearer ' + refresh_token})
