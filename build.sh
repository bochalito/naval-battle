echo "Buildng naval-battle project"
rm -r out/
mkdir out
javac -d out/ -cp src src/naval/battle/Game.java
cd out/
java naval.battle.Game
