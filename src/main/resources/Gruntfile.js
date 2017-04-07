(function () {
    module.exports = configureGrunt;

    function configureGrunt (grunt) {
        var npmTasks = [
            'grunt-contrib-jshint',
            'grunt-contrib-uglify',
            'grunt-contrib-less',
            'grunt-contrib-cssmin',
            'grunt-contrib-watch',
            'grunt-contrib-copy',
            'grunt-contrib-concat',
            'grunt-contrib-clean'
        ];

        grunt.initConfig({
            pkg: grunt.file.readJSON('package.json'),
            watch: getWatchConfig(),
            jshint: getJsHintConfig(),
            uglify: getUglifyConfig(),
            less: getLessConfig(),
            cssmin: getCssMinConfig(),
            copy: getCopyConfig(),
            concat: getConcatConfig(),
            clean: getCleanConfig()
        });

        npmTasks.forEach(function (npmTask) {
            grunt.loadNpmTasks(npmTask);
        });

        grunt.registerTask('default', [
            'jshint',
            'uglify',
            'less',
            'cssmin',
            'concat:vendorjs',
            'concat:vendorcss',
            'copy:templates',
            'clean:build'
        ]);
    }

    function getWatchConfig() {
        return {
            files: 'src/less/**/*.less',
            tasks: ['less', 'cssmin'],
            scripts: {
                files: 'src/app/**/*.js',
                tasks: ['jshint', 'uglify']
            }
        };
    }

    function getJsHintConfig() {
        return {
            options: {},
            build: ['Gruntfile.js', 'src/app/**/*.js']
        };
    }

    function getUglifyConfig() {
        return {
            options: {
                banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n',
                mangle: false
            },
            build: {
                files: {
                    "public/pokemon-main.min.js": 'src/app/pokemon.js',
                    "public/pokemon-modules.min.js": 'src/app/**/pokemon.*.js',
                    "public/pokemon-app.min.js": ['src/app/**/*.js', '!src/app/pokemon.js', '!src/app/**/pokemon.*.js']
                }
            }
        };
    }

    function getLessConfig() {
        return {
            build: {
                files: {
                    "build/app.css": 'src/less/app.less'
                }
            }
        };
    }

    function getCssMinConfig() {
        return {
            options: {
                banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n'
            },
            build: {
                files: {
                    "public/pokemon-app.min.css": 'build/app.css'
                }
            }
        };
    }

    function getCopyConfig() {
        return {
            templates: {
                src: 'src/app/**/*.tpl.html',
                dest: 'public/',
                expand: true
            }
        };
    }

    function getConcatConfig() {
        return {
            vendorjs: {
                src: [
                    'bower_components/jquery/dist/jquery.min.js',
                    'bower_components/angular/angular.min.js',
                    'bower_components/angular-resource/angular-resource.js',
                    'bower_components/angular-ui-router/release/angular-ui-router.js'
                ],
                dest: 'public/pokemon-vendor.js'
            },
            vendorcss: {
                src: [
                    'bower_components/bootstrap/dist/css/bootstrap.css'
                ],
                dest: 'public/pokemon-vendor.css'
            }
        };
    }

    function getCleanConfig() {
        return {
            build: ['build/**']
        };
    }
})();

