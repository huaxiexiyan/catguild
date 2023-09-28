# 应用模块，包含所有与应用逻辑相关的内容。
import os
import logging
from flask import Flask
from logging.config import dictConfig
from flask_redis import FlaskRedis

# 日志配置
dictConfig({
    'version': 1,
    'formatters': {'default': {
        'format': '[%(asctime)s] %(levelname)s in %(module)s: %(message)s',
    }},
    'handlers': {'wsgi': {
        'class': 'logging.StreamHandler',
        'stream': 'ext://flask.logging.wsgi_errors_stream',
        'formatter': 'default'
    }},
    'root': {
        'level': 'INFO',
        'handlers': ['wsgi']
    }
})

# 创建和配置应用
app = Flask(__name__, instance_relative_config=True)
app.config.from_mapping(
    SECRET_KEY='dev',
    DATABASE=os.path.join(app.instance_path, 'flaskr.sqlite'),
)

# 在未测试时加载实例配置（如果存在）
app.config.from_pyfile('config.py', silent=True)

# 确保实例文件夹存在
try:
    os.makedirs(app.instance_path)
except OSError:
    pass

# 配置日志
app.config['LOGGING_LEVEL'] = logging.INFO  # 设置日志级别为INFO
app.config['LOGGING_FORMAT'] = '%(asctime)s [%(levelname)s] - %(message)s'
app.config['LOGGING_LOCATION'] = 'app.log'  # 设置日志文件路径

# 配置Redis连接
app.config['REDIS_URL'] = ''
# 初始化Flask-Redis
redis_client = FlaskRedis(app, decode_responses=True)

# 注册路由
from app.api import aliyun_drive_task_api
app.register_blueprint(aliyun_drive_task_api.bp, url_prefix="/api/aliyun-drives")
