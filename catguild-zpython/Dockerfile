# 使用适当的 Debian 发行版构建虚拟环境
# * 为内置的 Python3 venv 模块安装 python3-venv（默认情况下未安装）
# * 安装 gcc lib python3-dev 来编译 C Python 模块
# * 在虚拟环境中：更新 pip setup和 wheel 以支持构建新包
FROM python:3.11.2 AS build
RUN pip install --upgrade pip setuptools wheel pipenv

# 将 pipenv 构建为单独的步骤：仅在依赖更改时重新执行此步骤
FROM build AS build-venv
COPY Pipfile /app/Pipfile
WORKDIR /app
#COPY Pipfile.lock /Pipfile.lock
RUN export PIPENV_VENV_IN_PROJECT=1 && \
    pipenv install

# 将虚拟环境复制到无发行版映像中
FROM gcr.io/distroless/python3-debian11
COPY --from=build-venv /app/.venv /app/.venv
COPY . /app
WORKDIR /app/catguild_spider
ENV PATH="/app/.venv/bin:$PATH"
RUN ls -a
#ENTRYPOINT ["sh", "-c", "(pipenv run scrapyd &); while ! nc -z localhost 6800; do sleep 1; done; scrapydweb"]
ENTRYPOINT ["sh", "-c", "pipenv run scrapyd & while ! python -c \"import socket; s = socket.socket(socket.AF_INET, socket.SOCK_STREAM); s.settimeout(1); result = s.connect_ex(('localhost', 6800)); s.close(); exit(result)\"; do sleep 1; done; pipenv run scrapydweb"]
