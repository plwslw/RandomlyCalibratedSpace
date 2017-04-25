all:
	make compile
	make script

compile: Pixel.java Canvas.java Matrix.java EdgeMatrix.java TMatrix.java Script.java 
	javac Pixel.java
	javac Canvas.java
	javac Matrix.java
	javac EdgeMatrix.java
	javac TMatrix.java
	javac Script.java

script:
	java Script scripts/script

script1:
	java Script scripts/script

clean: 
	rm *.class *.ppm *~ *.png *.jpg

