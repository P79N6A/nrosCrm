#!/bin/bash
#
# Copyright 2016 ZTE Corporation.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


DIRNAME=`dirname $0`
RUNHOME=`cd $DIRNAME/../; pwd`
JAVA="/usr/bin/java"
Main_Class="nros-crm-start-1.0-SNAPSHOT"
main_path=$RUNHOME
cd $main_path

JAVA_OPTS="-Xms128m -Xmx256m"
class_path=$main_path/$Main_Class.jar
echo ============$Main_Class environment============
echo @RUNHOME@ $RUNHOME	
echo @JAVA_HOME@ $JAVA_HOME
echo @JAVA_OPTS@ $JAVA_OPTS
echo @class_path@ $class_path
echo "java -jar" $JAVA_OPTS $class_path
echo ===============================================

rm -rf $RUNHOME/*.out
rm -rf $RUNHOME/logs/*.*
nohup $JAVA -jar $JAVA_OPTS $class_path &
