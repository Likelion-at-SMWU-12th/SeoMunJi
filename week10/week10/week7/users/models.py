from typing import Any
from django.db import models
from django.contrib.auth.models import AbstractUser, UserManager as DjangoUserManger
# Create your models here.

class UserManager(DjangoUserManger):
    def _create_user(self, username, email, password, **extra_fields):
        if not email: #이메일 유효성 검사
            raise ValueError("이메일은 필수 값입니다")
        user = self.model(username=username, email=email, **extra_fields)
        user.set_password(password)
        user.save(using=self.db)
        return user

    def create_user(self, username, email=None, password=None, **extra_fields):
        extra_fields.setdefault('is_staff', False)
        extra_fields.setdefault('is_superuser', False)
        return self._create_user(username, email, password, **extra_fields)
    
    def create_superuser(self, username, email=None, password=None, **extra_fields):
        extra_fields.setdefault('is_staff', True)
        extra_fields.setdefault('is_superuser', True)
        return super().create_superuser(username, email, password, **extra_fields)

class User(AbstractUser):
    phone = models.CharField(verbose_name='전화번호', max_length=11)
    objects = UserManager()