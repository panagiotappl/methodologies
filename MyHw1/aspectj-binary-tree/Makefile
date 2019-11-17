JAVA_FILES=Main.java BinaryTree.java
AJ_ASPECT=BinaryTreeAspect.aj

ASPECTJRT=/usr/share/java/aspectjrt.jar

justJavaCompile: ${JAVA_FILES}
	@javac -cp ${ASPECTJRT} ${JAVA_FILES}

justJavaRun: justJavaCompile
	@echo "== Running without AspectJ =="
	@java -Djava.util.concurrent.ForkJoinPool.common.parallelism=8 Main

aspectJCompile: ${JAVA_FILES} ${AJ_ASPECT}
	@ajc -source 1.8 ${JAVA_FILES} ${AJ_ASPECT}

aspectJRun: aspectJCompile
	@echo "== Running with AspectJ =="
	@java -Djava.util.concurrent.ForkJoinPool.common.parallelism=8 -cp ${ASPECTJRT}:. Main

clean: 
	@rm *.class