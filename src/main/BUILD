package(default_visibility = ["//visibility:public"])

java_plugin(
    name = "autovalue_plugin",
    deps = ["//external:autovalue"],
    processor_class = "com.google.auto.value.processor.AutoValueProcessor",
)

java_library(
    name = "autovalue",
    exports = ["//external:autovalue"],
    exported_plugins = [":autovalue_plugin"],
)
