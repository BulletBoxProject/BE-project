#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/BulletBox"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

sudo docker rm -f DOCKER_NAME=$(docker ps -qa)
echo "TIME_NOW > 현재 구동중인 컨테이너는 $DOCKER_NAME 입니다." >> $DEPLOY_LOG

sudo docker pull gksrywls97/bulletbox:web
sudo docker pull gksrywls97/bulletbox:redis
docker-compose up -d --remove-orphans
docker image prune -f

