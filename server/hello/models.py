from django.db import models


class Hello:
    def __init__(self, content):
        self.content = content

    def __str__(self):
        return "{}".format(self.content)


class Dog(models.Model):
    name = models.CharField(max_length=10)
    owner = models.ForeignKey('auth.User', related_name='dog', on_delete=models.CASCADE)

