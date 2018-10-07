from django.conf.urls import url
from . import views
from rest_framework.urlpatterns import format_suffix_patterns

urlpatterns = [
    url('normal', views.get_hello, name='hello'),
    url('serialized', views.get_hello_serialized, name='helloSerialized'),
    url(r'^dog/$', views.DogsList.as_view()),
    url(r'^dog/(?P<pk>[0-9]+)/$', views.DogDetails.as_view()),
    url('dog_clear', views.dogs_clear),
    url(r'^user/$', views.UserList.as_view()),
    url(r'^user/(?P<pk>[0-9]+)/$', views.UserDetails.as_view()),
]

urlpatterns = format_suffix_patterns(urlpatterns)
