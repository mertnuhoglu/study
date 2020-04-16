export GIT_USER_NAME=mertnuhoglu
git remote set-url origin --add git@gitlab.com:${GIT_USER_NAME}/${GIT_REPO_NAME}.git
git remote set-url origin --add git@bitbucket.org:${GIT_USER_NAME}/${GIT_REPO_NAME}.git
git remote -v
