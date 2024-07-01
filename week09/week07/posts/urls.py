from django.urls import path

from .views import post_create_view, post_delete_view
from .views import post_list_view, post_update_view, post_detail_view
from .views import post_form_view, post_create_form_view

app_name='posts'

urlpatterns=[
    path('', post_list_view, name='post-list'),
    path('form/', post_form_view, name='post-form'),
    path('new/', post_create_form_view, name='post-new'),
    path('<int:id>/', post_detail_view),
    path('<int:id>/edit', post_update_view),
    path('<int:id>/delete', post_delete_view),
]