from django.urls import path, include
from django.contrib import admin

from .views import post_create_view, post_delete_view
from .views import post_list_view, post_update_view, post_detail_view
from .views import post_form_view, post_create_form_view
from .views import PostModelViewSet
from rest_framework import routers

app_name='posts'

router = routers.DefaultRouter()
router.register('', PostModelViewSet)

urlpatterns=[
    path('', include(router.urls)),
    path('admin/', admin.site.urls),
    #path('', post_list_view, name='post-list'),
    #path('new/', post_create_view),
    #path('form/', post_form_view, name='post-form'),
    #path('new/', post_create_form_view, name='post-new'),
    #path('<int:id>/', post_detail_view),
    #path('<int:id>/edit', post_update_view),
    #path('<int:id>/delete', post_delete_view),
]
