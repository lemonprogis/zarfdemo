ARCH ?= amd64

.PHONY: all

docker-build:
	docker build -t lemonpro/jokes-api .

docker-run:
	docker run -it -p 8080:8080 lemonpro/jokes-api

zarf-create:
	zarf package create . --confirm --architecture ${ARCH}

zarf-deploy:
	zarf package deploy --confirm zarf*${ARCH}*.tar.zst
