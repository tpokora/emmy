from passlib.apps import custom_app_context as pwd_context
from flask_restful_swagger import swagger
from flask_restful import fields
from flask_security import UserMixin, RoleMixin, login_required
from server_flask.common.models import db


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
        'password_hash': fields.String
    }


roles_users = db.Table('roles_users',
                       db.Column('user_id', db.Integer(), db.ForeignKey('user.id')),
                       db.Column('role_id', db.Integer(), db.ForeignKey('role.id')))


@swagger.model
class Role(db.Model, RoleMixin):
    id = db.Column(db.Integer(), primary_key=True)
    name = db.Column(db.String(80), unique=True)
    description = db.Column(db.String(255))


@swagger.model
class User(db.Model, UserMixin):
    __tablename__ = 'user'
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(32), index=True)
    password_hash = db.Column(db.String(128))
    email = db.Column(db.String(255), unique=True)
    password = db.Column(db.String(255))
    active = db.Column(db.Boolean())
    created_at = db.Column(db.DateTime, default=db.func.current_timestamp())
    modified_at = db.Column(db.DateTime, default=db.func.current_timestamp(),
                            onupdate=db.func.current_timestamp())
    confirmed_at = db.Column(db.DateTime())
    last_login_at = db.Column(db.DateTime())
    current_login_at = db.Column(db.DateTime())
    last_login_ip = db.Column(db.String(45))
    current_login_ip = db.Column(db.String(45))
    login_count = db.Column(db.Integer)
    roles = db.relationship('Role', secondary=roles_users,
                            backref=db.backref('users', lazy='dynamic'))

    def hash_password(self, password):
        self.password_hash = pwd_context.hash(password)
        self.password = password

    def verify_password(self, password):
        return pwd_context.verify(password, self.password_hash)

    def __repr__(self):
        return '<User {}, {}>'.format(self.username, self.password_hash)

    def serialize(self):
        return {'id': self.id,
                'username': self.username,
                'password_hash': self.password_hash,
                'email': self.email,
                'password': self.password,
                'active': self.active,
                'confirmed_at': self.confirmed_at
                }

