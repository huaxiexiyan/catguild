# 使用适当的 Debian 发行版构建虚拟环境
# * 为内置的 Python3 venv 模块安装 python3-venv（默认情况下未安装）
# * 安装 gcc lib python3-dev 来编译 C Python 模块
# * 在虚拟环境中：更新 pip setup和 wheel 以支持构建新包
FROM python:3.11.2 AS build
RUN apt-get install -y coreutils
RUN ls
RUN pip install --upgrade pip setuptools wheel pipenv

# 将 pipenv 构建为单独的步骤：仅在依赖更改时重新执行此步骤
FROM build AS build-venv
COPY Pipfile /Pipfile
#COPY Pipfile.lock /Pipfile.lock
RUN export PIPENV_VENV_IN_PROJECT=1
RUN pipenv install
RUN ls

# 将虚拟环境复制到无发行版映像中
FROM gcr.io/distroless/python3-debian11
RUN ls
COPY --from=build-venv /.venv /.venv
COPY . /app
WORKDIR /app
RUN ls
ENTRYPOINT ["/venv/bin/python3", "psutil_example.py"]
