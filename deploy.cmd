git clone https://github.com/Icraus/j-be.git
pushd j-be
git fetch --all
git worktree remove -f ../FE 
git worktree remove -f ../mobile-client
git worktree add --force ../FE origin/FE
git worktree add --force ../mobile-client origin/mobile-client
call gradlew :release
start java -jar release\j-be-0.0.1-release.jar

pushd ..\FE
call gradlew :release
start  java -jar release\j-fe-0.0.1-release.jar
popd

pushd ..\mobile-client
call flutter pub get
call flutter build apk
popd

popd