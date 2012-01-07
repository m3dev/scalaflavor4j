#!/bin/sh
cd ~
HOME_DIR=`pwd`
cd -
mvn -DaltDeploymentRepository=release-repo::default::file:${HOME_DIR}/github/m3dev.github.com/mvn-repo/snapshots clean deploy

