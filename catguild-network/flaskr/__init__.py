import os
import logging
from flask import Flask, Blueprint
from logging.config import dictConfig

#  __init__.py 有两个作用：
#  一是包含应用工厂；
#  二是告诉 Python flaskr 文件夹应当视作为一个包。
def create_app(test_config=None):
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

    if test_config is None:
        # 在未测试时加载实例配置（如果存在）
        app.config.from_pyfile('config.py', silent=True)
    else:
        # 加载测试配置（如果传入）
        app.config.from_mapping(test_config)

    # 确保实例文件夹存在
    try:
        os.makedirs(app.instance_path)
    except OSError:
        pass

    # 配置日志
    app.config['LOGGING_LEVEL'] = logging.INFO  # 设置日志级别为INFO
    app.config['LOGGING_FORMAT'] = '%(asctime)s [%(levelname)s] - %(message)s'
    app.config['LOGGING_LOCATION'] = 'app.log'  # 设置日志文件路径

    # 注册路由
    from . import ali_network_disk_task
    app.register_blueprint(ali_network_disk_task.bp)

    return app
