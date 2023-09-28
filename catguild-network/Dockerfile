# 使用一个基础镜像，可以根据你的需求选择不同的Python版本
FROM python:3.11

# 设置工作目录
WORKDIR /app

# 复制应用程序文件到容器中
COPY run.py .

# 复制Pipenv文件到容器中，并安装依赖项
COPY Pipfile .
COPY Pipfile.lock .
RUN pip install pipenv && pipenv install --deploy --ignore-pipfile

# 暴露Flask应用程序的端口（通常是5000）
EXPOSE 5000

# 启动Flask应用程序
CMD ["pipenv", "run", "python", "run.py"]
