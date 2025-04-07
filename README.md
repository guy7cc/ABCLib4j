# ABCLib4j
## 内容
 競技プログラミングで汎用的なクラスのライブラリ、提出用のコード記述部分、および提出コードを1つのファイルにまとめるGradleタスク。
## 提出コードの生成
 /app/src/main/java/com/guy7cc/abclib4j/Main.javaに、提出コードのエントリポイントとなるコードを記述します。  
 次に、Gradleプロジェクトをロードし、submissionグループに含まれるprintSubmissionFileタスクを実行すると、提出用のコードが/app/build/generated/Main.javaに保存されます。
