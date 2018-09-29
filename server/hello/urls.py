from django.conf.urls import url
from . import views

urlpatterns = [
    url('normal', views.get_hello, name='hello'),
    url('serialized', views.get_hello_serialized, name='helloSerialized'),
    url('dog$', views.dogs),
    url('dog_clear', views.dogs_clear)
]
