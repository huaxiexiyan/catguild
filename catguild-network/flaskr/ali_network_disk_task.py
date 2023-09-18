from flask import jsonify, Blueprint, current_app as app

from flaskr.aliyundrive import Aliyundrive

bp = Blueprint('ali_network_disk', __name__, url_prefix='/api/aliNetworkDisk')


@bp.get("/dailyCheckIns")
def get_test():
    token_string = ''

    token_string = token_string.split(',')
    ali = Aliyundrive()
    message_all = []

    for idx, token in enumerate(token_string):
        result = ali.aliyundrive_check_in(token)
        message_all.append(str(result))

        if idx < len(token_string) - 1:
            message_all.append('--')

    title = '阿里云盘签到结果'
    app.logger.info('%s <====> %s', title, message_all)
    return jsonify("{'su':'true'}")
