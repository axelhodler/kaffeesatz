# Kaffeesatz
Offers some stats to a git repo

## Usage
The following will provide the Top 30 of the most changed files

    ./bin/run.sh git_repo_dir_of_choice

e.g. inside of this repo

    ./bin/run.sh $(pwd)

## Development

Run unit- and integrationtests via

    ./bin/run_integration_tests.sh

Run only unit tests via

    mvn clean test
