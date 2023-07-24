# travel-time

This is a program that records driving through using the Apple phone shortcut to connect and disconnect via Carplay.

## Getting Started

1. Registering an account
2. Obtaining an OpenID
3. Configuring automation on iPhone
4. Calling a driving record interface

## Build

```bash
mvn clean package -Dmaven.test.skip=true
```

## Run

```bash
java -jar jar/app.jar
```

## Docker

```bash
docker run -idt \
    --name drivetime \
    -p 8080:8080 \
    -v ./resources:/resources \
    travel-time
```

## [API Document](doc/travel-time.md)

## Log

### 2023/7/24 23:19:21

完成订阅服务，可以通过订阅服务获取到用户的行程信息
