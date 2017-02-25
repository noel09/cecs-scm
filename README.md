# cecs-scm
A software code management system
## Team CKC
* Vincent Cheong 
* Imanuel Kurniawan 
* Douglas Choi 

CECS 543 - Section 01
## Introduction
This project part is to form a development team and to build the first part of our SCM (Source Code Management) project (AKA a VCS: Version Control System). This part only implements an initial use-case: Create Repo. It also makes a number of simplifying assumptions in order to get to working S/W quickly. For background material, review on-line user documentation for Fossil, Git, and/or Subversion. Note, and “artifact” is a version of a file. The SCM repository will hold multiple versions of a given file, hence the original file name is not sufficient to distinguish between two of its artifacts; hence, within the repository we will use a code name for each artifact and will put all the artifacts of a particular file in a folder which has the original file's name.

## Requirements
* Java 1.8 
* Windows 7 or higher

## Build
* To compile: ```javac cecs/scm/Main.java```
* To run: ```java cecs.scm.Main```

## Usage
* To create a repository: ```CREATE <source-project-directory> <repository-directory>```

## Bugs
* Spaces in file names currently does not work.