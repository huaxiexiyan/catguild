import json

from flask import jsonify, make_response, Blueprint, current_app

from app.models.network_app_auth_config import NetworkAppAuthConfig
from app.service.aliyun_drive_task_service import AliyunDriveTaskService, SignInGoodsResponse
from app import redis_client

bp = Blueprint('ali_network_disk', __name__)


@bp.get("/daily-check-ins")
async def get_run():
    current_app.logger.info("阿里云盘签到任务开始")
    # 获取预期运行的列表
    redis_key = 'auth:network_app_auth_config:list:all'
    redis_access_key_per = 'auth:network_app_auth_config:new_auth:'
    list_len = redis_client.llen(redis_key)
    current_app.logger.info("预期将执行【%s】次签到", list_len)
    network_app_auth_config_list = redis_client.lrange(redis_key, 0, -1)
    # 遍历每个JSON字符串并将其转换为对象
    aliyun_drive_task = AliyunDriveTaskService()
    for json_str in network_app_auth_config_list:
        network_app_auth_config = NetworkAppAuthConfig(**json.loads(json_str))
        access_token = network_app_auth_config.accessToken
        # 检查今天是否签到
        sign_in_goods_response = aliyun_drive_task.get_sign_in_goods(access_token)
        if isinstance(sign_in_goods_response, tuple):
            # 这是一个元组
            is_success, result = sign_in_goods_response
            if is_success is False:
                # 如果access_token过期，则重新获取
                current_app.logger.info("当前用户【%s】的access_token已过期,重新获取", network_app_auth_config.id)
                account_token_bo = aliyun_drive_task.get_access_token(network_app_auth_config.refreshToken)
                if account_token_bo.success is False:
                    current_app.logger.info("当前用户【%s】的 refreshToken 失效了，需要手动重新更新，错误消息是【%s】",
                                            network_app_auth_config.id, account_token_bo.message)
                    continue
                access_token = account_token_bo.access_token
                redis_client.set(redis_access_key_per + network_app_auth_config.id, json.dumps(account_token_bo.__dict__))
                sign_in_goods_response = aliyun_drive_task.get_sign_in_goods(access_token)
        # 判断今天是否已经签到过了
        if sign_in_goods_response.isSignIn is True:
            current_app.logger.info("当前用户【%s】已签到,跳过执行下一条", network_app_auth_config.id)
            continue
        # 获取签到日期
        sign_in_list = aliyun_drive_task.get_sign_in_list(access_token)
        # 执行签到
        aliyun_drive_task.get_reward(access_token, sign_in_list.signInCount)
        current_app.logger.info("当前用户【%s】已签到完成", network_app_auth_config.id)
    current_app.logger.info("阿里云盘签到任务结束")
    return make_response('')
