FROM openjdk:17-jdk-oracle
MAINTAINER peixinyi
# 设置容器环境变量
ARG SOFTWARE_VERSION
ARG SOFTWARE_VERSION_DATE
ENV SOFTWARE_VERSION ${SOFTWARE_VERSION}
ENV SOFTWARE_VERSION_DATE ${SOFTWARE_VERSION_DATE}

# 拷贝jar包
ENV JAR_PATH jar/app.jar
# 拷贝执行Jar包
COPY $JAR_PATH app.jar
# 设置配置文件路径
ENV CONFIG_PATH /resources/application.yaml
ENV RUN_JAR_PATH app.jar
# 设置时区
RUN echo 'Asia/Shanghai' >/etc/timezone
# 暴露端口
EXPOSE 8080
# 挂载配置文件
VOLUME /resources
# 执行Jar包
ENTRYPOINT ["sh","-c","java -jar $RUN_JAR_PATH --spring.config.location=$CONFIG_PATH"]