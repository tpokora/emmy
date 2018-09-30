from django.conf.urls import url
from . import views

urlpatterns = [
    url('normal', views.get_hello, name='hello'),
    url('serialized', views.get_hello_serialized, name='helloSerialized'),
    url(r'^dog/$', views.dogs),
    url(r'^dog/(?P<pk>[0-9]+)/$', views.dog_detail),
    url('dog_clear', views.dogs_clear)
]
