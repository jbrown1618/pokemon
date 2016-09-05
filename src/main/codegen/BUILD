package(default_visibility = ["//visibility:public"])

py_library(
    name = "codegen",
    srcs = glob(["*.py"]),
    data = ["//main/data"],
    deps = [],
)

# To manually generate the Species.java and Move.java files, run
# the following from the 'main' directory (or change the java_base_dir
# path accordingly):
#
#   $ bazel run //codegen:generate_pokemon -- --version=silver --java_base_dir=`pwd`/java
#

py_binary(
    name = "generate_pokemon",
    srcs = ["generate_pokemon.py"],
    deps = [":codegen"],
)
