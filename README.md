# laba1

Clusterization on clojure

## Usage

`lein run <path_to_data_file> <name_of_distance_function> <radius> `

* `<path_to_data_file>` - path to the data. Only my files in ./examples/ directory!
* `<radius>` - radius.
* `<distance_func_name>` - 'euclidean', 'hamming', 'manhattan'.

## Output

Since 0 list of indexes from the input file

## Example


```
Andrey:fp cfc1020$ lein run examples/glass.data.txt euclidean 0.5
60 , 6 , 62 , 96 , 195 , 172 , 123 , 54 , 116 , 191 , 211 , 210 , 158 , 39 , 66 , 75 , 147 , 23
```


```
Andrey:fp cfc1020$ lein test

lein test laba1.core-test

Ran 3 tests containing 6 assertions.

0 failures, 0 errors.
```


## For start of tests


`lein test`

!!!!!


