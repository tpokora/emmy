from django.db import models

# Create your models here.


class HolidayCounter(models.Model):
    deadline = models.DateTimeField(default='', blank=False)
    title = models.CharField(max_length=100, blank=True, default='')