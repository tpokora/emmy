from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()


class Message:
    def __init__(self, message_string):
        self.message = message_string

    def print(self):
        return self.__dict__
