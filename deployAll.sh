#!/bin/bash

WICKET_MENU_VERSION = 2

WICKET_VERSIONS=(6.9.0 6.9.1 6.10.0)
echo ${ARRAY[*]}


## declare an array variable
declare -a arr=("6.9.0" "6.9.1" "6.10.0")

## now loop through the above array
for i in "${arr[@]}"
do
   echo "$i"
   eval "git checkout  $i"
   eval "mvn versions:set -DnewVersion=$i.$WICKET_MENU_VERSION"
   eval "git commit -m 'Release $i.$WICKET_MENU_VERSION'"
   git push
#   mvn clean deploy
#   create relase tag

done



# You can access them using echo "${arr[0]}", "${arr[1]}" also


#git checkout  6.9.0
#mvn versions:set -DnewVersion=6.9.0.2
#git commit -m "Release 6.9.0.2"
#git push
#mvn clean deploy
#
#
#git checkout  6.9.1
#mvn versions:set -DnewVersion=6.9.1.2
#git commit -m "Release 6.9.1.2"
#git push
#mvn clean deploy
#
#
#git checkout  6.10.0
#mvn versions:set -DnewVersion=6.10.0.2
#git commit -m "Release 6.10.0.2"
#git push
#mvn clean deploy
#
#git checkout  6.11.0
#mvn versions:set -DnewVersion=6.11.0.2
#git commit -m "Release 6.11.0.2"
#git push
#mvn clean deploy
#
#git checkout  6.12.0
#mvn versions:set -DnewVersion=6.12.0.2
#git commit -m "Release 6.12.0.2"
#git push
#mvn clean deploy
#
#git checkout  6.13.0
#mvn versions:set -DnewVersion=6.13.0.2
#git commit -m "Release 6.13.0.2"
#git push
#mvn clean deploy
#
#git checkout  6.14.0
#mvn versions:set -DnewVersion=6.14.0.2
#git commit -m "Release 6.14.0.2"
#git push
#mvn clean deploy
#
#git checkout  6.15.0
#mvn versions:set -DnewVersion=6.15.0.2
#git commit -m "Release 6.15.0.2"
#git push
#mvn clean deploy
#
#git checkout  6.16.0
#mvn versions:set -DnewVersion=6.16.0.2
#git commit -m "Release 6.16.0.2"
#git push
#mvn clean deploy
#
#git checkout  6.17.0
#mvn versions:set -DnewVersion=6.17.0.2
#git commit -m "Release 6.17.0.2"
#git push
#mvn clean deploy
#
#git checkout  6.18.0
#mvn versions:set -DnewVersion=6.18.0.2
#git commit -m "Release 6.18.0.2"
#git push
#mvn clean deploy
#
#git checkout  6.19.0
#mvn versions:set -DnewVersion=6.19.0.2
#git commit -m "Release 6.19.0.2"
#git push
#mvn clean deploy
#
#
#git checkout  6.20.0
#mvn versions:set -DnewVersion=6.20.0.2
#git commit -m "Release 6.20.0.2"
#git push
#mvn clean deploy
#
#
#git checkout  7.0.0
#mvn versions:set -DnewVersion=7.0.0.2
#git commit -m "Release 7.0.0.2"
#git push
#mvn clean deploy




