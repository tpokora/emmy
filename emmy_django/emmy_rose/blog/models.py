from django.db import models
from django.contrib.auth.models import User

# Create your models here.


class Entry(models.Model):
    created = models.DateTimeField(auto_now_add=True)
    title = models.CharField(max_length=100, blank=True, default='')
    content = models.TextField(blank=True, default='')
    user = models.ForeignKey(User, on_delete=models.CASCADE, )

    class Meta:
        ordering = ('created',)
