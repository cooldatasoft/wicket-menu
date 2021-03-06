#!/bin/bash
pwd

WICKET_MENU_VERSION="4"
## declare versions of wicket that wicket-menu maintains
declare -a WICKET_VERSIONS=("6.9.0" "6.9.1" "6.10.0" "6.11.0" "6.12.0" "6.13.0" "6.14.0" "6.15.0" "6.16.0" "6.17.0" "6.18.0" "6.19.0" "6.20.0" "7.0.0", "7.1.0")
#declare -a WICKET_VERSIONS=("6.9.1")

## now loop through the above array
for wicketVersion in "${WICKET_VERSIONS[@]}"
do
   echo "$wicketVersion"
   eval "git checkout  $wicketVersion"

#   cahce username/password for git
   git config credential.helper store
   git fetch origin
   git pull


   eval "mvn versions:set -DnewVersion=$wicketVersion.$WICKET_MENU_VERSION -DgenerateBackupPoms=false"

   eval "rm wicket-menu-demo/src/main/java/com/cooldatasoft/app/WicketMenuDemoApplication.properties"

   echo "wicketmenu.version = $wicketVersion.$WICKET_MENU_VERSION" >> wicket-menu-demo/src/main/java/com/cooldatasoft/app/WicketMenuDemoApplication.properties

   git add .

   eval "git commit -m 'Release $wicketVersion.$WICKET_MENU_VERSION'"
   git push

   mvn clean deploy

   echo Creating release on github...
   GITHUB_TOKEN=$(head -n 1 ~/.m2/github-token)
   eval "curl -# -XPOST -H 'Content-Type:application/json' -H 'Accept:application/json' -d  '{\"tag_name\": \"$wicketVersion.$WICKET_MENU_VERSION\", \"target_commitish\": \"$wicketVersion\", \"name\": \"wicket-menu-$wicketVersion.$WICKET_MENU_VERSION\", \"body\": \"wicket-menu for using with wicket version $wicketVersion\",  \"draft\": false,  \"prerelease\": false}'  https://api.github.com/repos/cooldatasoft/wicket-menu/releases?access_token=$GITHUB_TOKEN"
done


git checkout master

