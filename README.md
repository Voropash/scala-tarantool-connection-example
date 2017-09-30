# first step for NifantovaIrina/scala-tarantool

## How to run connection tests?

1. Run docker container with tarantool. Port forward its port - share this with host machine
    docker run --rm -t -i -p 3301:3301 tarantool/tarantool:1.7
2. Run "test" task in sbt shell (or use IDEA configuration with sbt test)
    sbt test
