"""Helper Methods"""


class TestHelpers:
    @staticmethod
    def create_user(test_app, user):
        return test_app.post('/flask/user', data={'username': user.username, 'password': user.password})

    @staticmethod
    def login_user(test_app, user):
        return test_app.post('/flask/login', data={'username': user.username, 'password': user.password})

