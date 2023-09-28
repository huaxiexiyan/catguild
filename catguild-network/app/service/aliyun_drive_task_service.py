import json
from typing import Tuple, Any

import pytz
import datetime
import requests
from flask import current_app as app

from app.models.aliyun_drive_info import SignInListResponse, CommonResponse, SignInGoodsResponse, AccountTokenBO


class AliyunDriveTaskService:
    """
    阿里云云盘任务服务
    """

    @classmethod
    def get_access_token(cls, refresh_token: str) -> AccountTokenBO:
        """
        获取access_token
        :rtype: AccountTokenResponse
        :param refresh_token:  阿里云盘刷新token
        :return:  tuple[0]: 是否成功请求token
                  tuple[1]: 用户名
                  tuple[2]: access_token
                  tuple[3]: refresh_token
                  tuple[4]: expires_in
                  tuple[5]: message
        """
        url = 'https://auth.aliyundrive.com/v2/account/token'
        payload = {'grant_type': 'refresh_token', 'refresh_token': refresh_token}
        response = requests.post(url, json=payload, timeout=5)
        data_json = response.json()

        if 'code' in data_json and data_json['code'] in ['RefreshTokenExpired', 'InvalidParameter.RefreshToken',
                                                         'InvalidParameterMissing.RefreshToken']:
            return AccountTokenBO(False, '', '', '', '', data_json['message'])

        nick_name, user_name = data_json['nick_name'], data_json['user_name']
        name = nick_name if nick_name else user_name
        access_token = data_json['access_token']
        refresh_token = data_json['refresh_token']
        expires_in = data_json['expires_in']
        return AccountTokenBO(True, name, access_token, refresh_token, expires_in, '')

    @classmethod
    def get_sign_in_list(cls, access_token: str) -> SignInListResponse:
        """
        获取签到列表
        :param access_token: token令牌
        :return:
        """
        url = 'https://member.aliyundrive.com/v2/activity/sign_in_list'
        payload = {}
        params = {'_rx-s': 'mobile'}
        headers = {'Authorization': f'Bearer {access_token}'}

        response = requests.post(url, json=payload, params=params, headers=headers, timeout=5)
        aliyun_drive_response = CommonResponse(**response.json())
        return SignInListResponse(**aliyun_drive_response.result)

    @classmethod
    def get_reward(cls, access_token: str, sign_day: int):
        """
        获得奖励
        :param access_token:  调用_get_access_token方法返回的access_token
        :param sign_day: 领取第几天
        :return: 签到请求结果
        """
        url = 'https://member.aliyundrive.com/v1/activity/sign_in_reward'
        payload = {'signInDay': sign_day}
        params = {'_rx-s': 'mobile'}
        headers = {'Authorization': f'Bearer {access_token}'}

        response = requests.post(url, json=payload, params=params, headers=headers, timeout=5)
        data = response.json()

        return data

    @classmethod
    def get_sign_in_goods(cls, access_token: str) -> tuple[bool, Any] | SignInGoodsResponse:
        """
        获取今天的签到情况
        :param access_token: access_token
        :return: False 或 SignInGoodsResponse
        """
        url = 'https://member.aliyundrive.com/v1/activity/sign_in_goods'
        payload = {}
        params = {'_rx-s': 'mobile'}
        headers = {'Authorization': f'Bearer {access_token}'}

        response = requests.post(url, json=payload, params=params, headers=headers, timeout=5)
        data_json = response.json()

        if 'code' in data_json and data_json['code'] in ['InternalError', 'AccessTokenInvalid']:
            return False, data_json['message']
        common_response = CommonResponse(**data_json)
        return SignInGoodsResponse(**common_response.result)
