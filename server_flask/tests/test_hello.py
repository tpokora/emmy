import unittest
from server_flask.app import app


class Test(unittest.TestCase):

    def test_hello(self):
        self.test_app = app.test_client()

        response = self.test_app.get('/flask/hello')
        self.assertEqual(response.status, "200 OK")
        self.assertEqual(response.json['message'], 'Hello')


if __name__ == '__main__':
    unittest.main()
