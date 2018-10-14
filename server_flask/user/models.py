from passlib.apps import custom_app_context as pwd_context
from flask_restful_swagger import swagger
from server_flask.common.models import db


@swagger.model
class User(db.Model):
    __tablename__ = 'users'
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(32), index=True)
    password_hash = db.Column(db.String(128))

    def __init__(self, name):
        self.name = name

    def hash_password(self, password):
        self.password_hash = pwd_context.hash(password)

    def verify_password(self, password):
        return pwd_context.verify(password, self.password_hash)

    def __init__(self, name):
        self.name = name

    def __repr__(self):
        return '<User {}, {}>'.format(self.name, self.password_hash)

    def serialize(self):
        return {"name": self.name,
                "password_hash": self.password_hash}
