# 用于启动应用的脚本。
# run.py
from app import app


def list_routes():
    output = []
    for rule in app.url_map.iter_rules():
        options = {}
        for arg in rule.arguments:
            options[arg] = "[{0}]".format(arg)
        methods = ','.join(rule.methods)
        url = rule.rule
        line = "{:50s} {:20s} {}".format(url, methods, options)
        output.append(line)
    return "\n".join(output)


if __name__ == '__main__':
    print(list_routes())
    app.run(debug=True)




