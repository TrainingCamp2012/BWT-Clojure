(defproject BWT-Clojure "BWT-Clojure-0.0.1"
  :description "Full text search in Clojure using Burrows-Wheeler Transform"
  :dependencies [[org.clojure/clojure "1.2.1"]
                 [org.clojure/clojure-contrib "1.2.0"]]
  :dev-dependencies [[speclj "2.1.1"]
                     [speclj-growl "1.0.1"]]
  :test-path "spec/"
  :jvm-opts ["-Xmx3g" "-server" "-Dfile.encoding=UTF-8"]
  :main BWT-Clojure.core)
