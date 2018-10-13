from flask_restful_swagger import swagger
from server_flask.common.models import db


@swagger.model
class Exercise(db.Model):
    __tablename__ = 'exercises'
    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(50), unique=True)

    def __init__(self, name=None):
        self.name = name

    def __repr__(self):
        return '<Exercise %r>' % self.name

    def serialize(self):
        return {"name": self.name}
