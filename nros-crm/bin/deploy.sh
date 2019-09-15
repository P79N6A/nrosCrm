#!/bin/bash
API_NAME="crm"
API_VERSION="0.0.1"
IMAGE_NAME="127.0.0.1:5000/$API_NAME:$BUILD_NUMBER"
CONTAINER_NAME=$API_NAME-$API_VERSION
ROOT_PATH="/home/trt/nros/crm"

#sudo mkdir $ROOT_PATH
sudo cp ./start/target/nros-crm-start-1.0-SNAPSHOT.jar $ROOT_PATH
sudo cp -r ./bin $ROOT_PATH

echo "Stop process..."
sudo sh $ROOT_PATH/bin/stop.sh
sleep 3
echo "Stop process...ok"
sleep 1
echo "Start process..."
sudo sh $ROOT_PATH/bin/start.sh

#构建docker 镜像
#sudo docker build -t $IMAGE_NAME .

#推送docker镜像
#sudo docker push $IMAGE_NAME

#删除同名docker容器
#cid=$(sudo docker ps | grep "$CONTAINER_NAME" | awk '{print $1}')
#if [ "$cid" != "" ]; then
#   sudo docker rm -f $cid
#fi



