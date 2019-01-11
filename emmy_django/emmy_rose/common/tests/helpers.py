from rest_framework.test import APIRequestFactory
from rest_framework.utils import json
from rest_framework_jwt.views import obtain_jwt_token


def login(username, password):
    url = '/auth-jwt'
    login_request = {'username': username, 'password': password}
    factory = APIRequestFactory()
    request = factory.post(url, data=json.dumps(login_request), content_type='application/json')
    response = obtain_jwt_token(request)
    token = response.data['token']
    return token
