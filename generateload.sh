#!/bin/bash

for i in {1..3}
do 
 curl -ik "http://localhost:9000/health"
 curl -ik "http://localhost:9000/joke/animal"
 curl -ik "http://localhost:9000/joke/daddy"
 curl -ik "http://localhost:9000/joke/random"
done



exit 0
