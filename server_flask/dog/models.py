from flask_restful_swagger import swagger


@swagger.model
class Dog:
    def __init__(self, name, age, color):
        self.name = name
        self.age = int(age)
        self.color = color

    def serialize(self):
        return self.__dict__