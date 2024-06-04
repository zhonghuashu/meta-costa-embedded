require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL:append = " \
                        dropbear \
                        hello-make \
                        hello-cmake \
                        helloworld \
                        hello-autotools \
                        lz4 \
                        bbexample \
                        sayhello \
                        "