JC = javac

.SUFFIXES: .java .class

.java.class:
		$(JC) $*.java

CLASSES = src/network/Client.java \
        src/network/Payload.java \
        src/network/Server.java \
        src/network/SocketListener.java \
        src/network/SocketManager.java \
		src/pijakogui/*.java

all:
	$(JC) $(CLASSES)

run:
	cd src; java network.Client
	
classes: $(CLASSES:.java=.class)

clean:
		$(RM) src/network/*.class
		$(RM) src/pijakogui/*.class