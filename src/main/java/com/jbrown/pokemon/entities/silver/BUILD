package(default_visibility = ["//visibility:public"])

java_library(
    name = "silver",
    srcs = [":create_enums"],
    deps = [
        "//external:guava",
        "//main:autovalue",
        "//main/java/com/jbrown/pokemon/enums",
        ":create_enums",
    ],
)

genrule(
    name = "create_enums",
    srcs = [],
    outs = [
        "Species.java",
        "Move.java",
    ],
    tools = ["//main/codegen:generate_pokemon"],
    cmd = "$(location //main/codegen:generate_pokemon) --version=silver --java_base_dir=$(GENDIR)/main/java",
)
