from flask_restful_swagger import swagger
from flask_restful import fields
from server_flask.common.models import db
from passlib.hash import pbkdf2_sha256 as sha256


class UserResourcesList:
    user_list = {
        'id': fields.Integer,
        'username': fields.String
    }

    user_get = {
        'id': fields.Integer,
        'username': fields.String
    }

    user_get_details = {
        'id': fields.Integer,
        'username': fields.String,
    }


@swagger.model
class User(db.Model):
    __tablename__ = 'user'

    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(120), unique=True, nullable=False)
    password = db.Column(db.String(120), nullable=False)

    def __repr__(self):
        return '<User {}, {}>'.format(self.username, self.password)

    @staticmethod
    def generate_hash(password):
        return sha256.hash(password)

    @staticmethod
    def verify_hash(password, hash):
        return sha256.verify(password, hash)

    def serialize(self):
        return {'id': self.id,
                'username': self.username,
                'password': self.password,
                }

