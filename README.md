# Boolean Function Representation

This is an implementation of Tower Algorithm as explained in [Neural Network Learning and Expert Systems](https://mitpress.mit.edu/books/neural-network-learning-and-expert-systems).

## How to build
> mvn clean package

It will build 2 jars: algo-tower.jar (without dependencies) and algo-tower-jar-with-dependencies.jar.
  
## How to run
### Arguments
    nn = mandatory argument of filepath to neural-net definition.
    ts = mandatory argument of filepath to training set.
    tower = optional argument of tower count to be build.

1. Boolean Function AND
> java -jar target/algo-tower-jar-with-dependencies.jar nn=neural-net-store/null.json ts=dataitem-store/and.json<br/>

2. Boolean Function OR
> java -jar target/algo-tower-jar-with-dependencies.jar nn=neural-net-store/null.json ts=dataitem-store/or.json<br/>

3. Boolean Function XOR
> java -jar target/algo-tower-jar-with-dependencies.jar nn=neural-net-store/null.json ts=dataitem-store/xor.json tower=1
