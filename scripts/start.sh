#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/BulletBox"
JAR_FILE="bulletBox-webapp.jar"

cd $PROJECT_ROOT

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# build 파일 복사
echo "$TIME_NOW > $JAR_FILE 파일 복사" >> $DEPLOY_LOG
cp ./build/libs/*.jar $PROJECT_ROOT/

# jar 파일 실행
if [ -z $CURRENT_PID ]; then
  echo "$TIME_NOW > 현재 실행중인 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_PID 애플리케이션 종료 " >> $DEPLOY_LOG
  kill -15 $CURRENT_PID
  sleep 5
fi

echo "$TIME_NOW > $JAR_FILE 파일 실행" >> $DEPLOY_LOG

echo nohup java -jar $JAR_FILE > $APP_LOG > $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG