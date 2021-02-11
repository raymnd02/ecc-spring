[INFO] Scanning for projects...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Build Order:
[INFO] 
[INFO] spring-activity                                                    [pom]
[INFO] core                                                               [pom]
[INFO] model                                                              [jar]
[INFO] dao                                                                [jar]
[INFO] service                                                            [jar]
[INFO] app                                                                [jar]
[INFO] 
[INFO] -------------------< com.exist.ecc:spring-activity >--------------------
[INFO] Building spring-activity 0.0.1-SNAPSHOT                            [1/6]
[INFO] --------------------------------[ pom ]---------------------------------
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for spring-activity 0.0.1-SNAPSHOT:
[INFO] 
[INFO] spring-activity .................................... FAILURE [  0.010 s]
[INFO] core ............................................... SKIPPED
[INFO] model .............................................. SKIPPED
[INFO] dao ................................................ SKIPPED
[INFO] service ............................................ SKIPPED
[INFO] app ................................................ SKIPPED
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.106 s
[INFO] Finished at: 2021-02-10T13:12:59+08:00
[INFO] ------------------------------------------------------------------------
[ERROR] Unknown lifecycle phase "app". You must specify a valid lifecycle phase or a goal in the format <plugin-prefix>:<goal> or <plugin-group-id>:<plugin-artifact-id>[:<plugin-version>]:<goal>. Available lifecycle phases are: validate, initialize, generate-sources, process-sources, generate-resources, process-resources, compile, process-classes, generate-test-sources, process-test-sources, generate-test-resources, process-test-resources, test-compile, process-test-classes, test, prepare-package, package, pre-integration-test, integration-test, post-integration-test, verify, install, deploy, pre-clean, clean, post-clean, pre-site, site, post-site, site-deploy. -> [Help 1]
[ERROR] 
[ERROR] To see the full stack trace of the errors, re-run Maven with the -e switch.
[ERROR] Re-run Maven using the -X switch to enable full debug logging.
[ERROR] 
[ERROR] For more information about the errors and possible solutions, please read the following articles:
[ERROR] [Help 1] http://cwiki.apache.org/confluence/display/MAVEN/LifecyclePhaseNotFoundException
