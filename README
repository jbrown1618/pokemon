Work-in-progress: An attempt at a Pokemon battle emulator.

---

## Development

### Command-Line

To compile / build from source, you'll want to download and
install [Bazel](https://bazel.io/versions/master/docs/install.html).

Then, try something like the following (from the `src` directory):

```
$ bazel build //main/java/...
$ bazel test //test/java/
```

### IntelliJ

First, you'll need to install Bazel *(see above)*.

Then, open Intellij:

1. Open the settings menu (`File > Settings)
1. Go to the `Plugins` tab
1. Select `Browse Repositories`
1. Search for `IntelliJ with Bazel`, and install
1. Restart IntelliJ to activate plugin changes
1. Select `File > Import Bazel Project`

    a. The workspace is `${GIT_ROOT}/src`, where the `WORKSPACE` file is located
    a. Import your `.bazelproject` file from the workspace:
       `${GIT_ROOT}/src/.bazelproject`
    a. Click `Finish`

Congratulations, you should now be able to develop using IntelliJ with Bazel.

* To rebuild dependencies when new ones are added (e.g. new external
  libraries), click the red `B` symbol in the toolbar, or select `Bazel > Sync
  Project with BUILD files`.
* To run tests, select `Run > Run` and you should be given the option of
  running a Bazel test.

## Local Testing and Deployment

TODO(jbrown1618): How to run/test the UI.
