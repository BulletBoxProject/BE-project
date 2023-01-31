#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/BulletBox"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# 현재 구동중인 어플리케이션 PID 확인
CURRENT_PID=$(sudo docker container ls -q)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG

if [ -z $CURRENT_PID ]; then
  echo "$TIME_NOW > 현재 구동중인 Docker 컨테이너가 없으므로 종료하지 않습니다." >> $DEPLOY_LOG
else
  echo "$TIME_NOW > sudo docker stop $CURRENT_PID"    # 현재 구동중인 Docker 컨테이너가 있다면, 모두 중지
  sudo docker stop $CURRENT_PID
  sleep 5
fi

# docker 컨테이너 실행
#sudo docker run -d -p 8080:8080 bulletbox             # -d(detach): 백그라운드 실행(nohup 처럼), -p(publish): 호스트 포트:도커 컨테이너 포트 -> 포트포워딩