from django.urls import path
#from .views import signup_view, login_view, logout_view
from .views import UserCreate, UserDetail
from rest_framework.authtoken.views import obtain_auth_token

app_name = 'accounts'

urlpatterns = [
    #path('signup/', signup_view, name = 'signup'),
    #path('login/', login_view, name = 'login'),
    #path('logout/', logout_view, name='logout'),
    path('users/', UserCreate.as_view(), name='user-create'),
    path('users/<int:pk>/', UserDetail.as_view(), name='user-detail'),
    path('login/', obtain_auth_token, name='login'),
]