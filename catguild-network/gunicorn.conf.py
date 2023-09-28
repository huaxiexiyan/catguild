import gevent.monkey

gevent.monkey.patch_all()

import multiprocessing

loglevel = 'info'
bind = "0.0.0.0:22001"
# 配置了日志就需要目录存在，否则会启动失败
# pidfile = "log/gunicorn.pid"
# accesslog = "log/access.log"
# errorlog = "log/debug.log"
# True 就是后台启动
# daemon = True

# 启动的进程数
workers = multiprocessing.cpu_count()
worker_class = 'gevent'
x_forwarded_for_header = 'X-FORWARDED-FOR'
