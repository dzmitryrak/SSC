#!/bin/bash

NODE_TOTAL=${CIRCLE_NODE_TOTAL:-1}
NODE_INDEX=${CIRCLE_NODE_INDEX:-0}

i=0
tests=()
for file in $(find ./src/test/java/com/itechart/tests -name "*Test.java" | sort)
do
  if [ $(($i % ${NODE_TOTAL})) -eq ${NODE_INDEX} ]
  then
    test=`basename $file | sed -e "s/.java//"`
    tests+="${test},"
  fi
  ((i++))
done

mvn mvn -Dtest=${tests} test > build_status.log