from sqlalchemy import Column, Integer, String
from server_flask.database import Base
from flask_restful_swagger import swagger


@swagger.model
class Exercise(Base):
    __tablename__ = 'exercises'
    id = Column(Integer, primary_key=True)
    name = Column(String(50), unique=True)

    def __init__(self, name=None):
        self.name = name

    def __repr__(self):
        return '<Exercise %r>' % self.name

    def serialize(self):
        return {"name": self.name}
