#!/bin/bash
pwd

WICKET_MENU_VERSION="3"
## declare versions of wicket that wicket-menu maintains
#declare -a WICKET_VERSIONS=("6.9.0" "6.9.1" "6.10.0" "6.11.0" "6.12.0" "6.13.0" "6.14.0" "6.15.0" "6.16.0" "6.17.0" "6.18.0" "6.19.0" "6.20.0" "7.0.0")
declare -a WICKET_VERSIONS=("6.9.0")

## now loop through the above array
for wicketVersion in "${WICKET_VERSIONS[@]}"
do
   echo "$wicketVersion"
   eval "git checkout  $wicketVersion"

#   cahce username/password for git
   git config credential.helper store
   git fetch origin
   git pull

   git cherry-pick ce8d8bd545b90f3e59c60a8ee417720308e77a1d
   git cherry-pick 2de128dd0f0e64e5e7cacb153b8d52ad2d2d6700
   git cherry-pick 6c822c7e67389d838e75252fc3e6875f469e85ab
   git cherry-pick 05a2699aff4926a8f332338c65bdd35baa97e76f
   git cherry-pick f6c5b69bc71d1dc0407181c29640c46daeaef2bf
   git cherry-pick 9c98e9ca1e17ccfe55f21b355d9e399fefdc8c98
   git cherry-pick 8afc8235870884c252cb2dc2697fac35d916bba0
   git cherry-pick 3a59404392a982eef0e5b904cc348e1dd3d1f2e6
   git cherry-pick 3a296688361c3f13cdefe8a709ec6a527377360b
   git cherry-pick efa2ec2a918217abdf8a7e44fc844c829312b4d5
   git cherry-pick a3571e8845532cdfad98ee71ea0602383337b638

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
