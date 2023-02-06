#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/BulletBox"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# 현재 구동중인 어플리케이션 PID 확인
#CURRENT_PID=$(sudo docker container ls -q)
#echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG
#
#if [ -z $CURRENT_PID ]; then
#  echo "$TIME_NOW > 현재 구동중인 Docker 컨테이너가 없으므로 종료하지 않습니다." >> $DEPLOY_LOG
#else
#  echo "$TIME_NOW > sudo docker stop $CURRENT_PID"    # 현재 구동중인 Docker 컨테이너가 있다면, 모두 중지
#  sudo docker stop $CURRENT_PID
#  sleep 5
#fi

# Blue 기준 현재 구동중인 컨테이너 체크
EXIST_BLUE=$(docker-compose -p web-blue -f docker-compose.blue.yml ps | grep Up)
echo "$TIME_NOW > 현재 구동중인 컨테이너는 $EXIST_BLUE 입니다." >> $DEPLOY_LOG

# 컨테이너 스위칭
if [ -z $EXIST_BLUE ]; then
  echo "$TIME_NOW > blue up" >> $DEPLOY_LOG
  cd $PROJECT_ROOT
  docker-compose -p web-blue -f docker-compose.blue.yml up -d
  BEFORE_COMPOSE_COLOR="green"
  AFTER_COMPOSE_COLOR="blue"
else
  echo "$TIME_NOW > green up" >> $DEPLOY_LOG
  cd $PROJECT_ROOT
  docker-compose -p web-green -f docker-compose.green.yml up -d
  BEFORE_COMPOSE_COLOR="blue"
  AFTER_COMPOSE_COLOR="green"
  sleep 10
fi

# 새로운 컨테이너 정상 작동 확인
EXIST_AFTER=$(docker-compose -p web-$AFTER_COMPOSE_COLOR -f docker-compose.$AFTER_COMPOSE_COLOR.yml ps | grep Up)
if [ -n $EXIST_AFTER ]; then
  # nginx.conf 를 컨테이너의 색상에 맞게 변경해주고 reload 진행
  cp /etc/nginx/nginx.$AFTER_COMPOSE_COLOR.conf /etc/nginx/nginx.conf
  nginx -s reload

  # 이전 컨테이너 종료
  docker-compose -p web-$BEFORE_COMPOSE_COLOR -f docker-compose.$BEFORE_COMPOSE_COLOR.yml down
  echo "$TIME_NOW > $BEFORE_COMPOSE_COLOR down" >> $DEPLOY_LOG