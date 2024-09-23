require recipes-core/images/core-image-minimal.bb

IMAGE_INSTALL:append = " dropbear \
                        procps \
                        vsftpd \
                        htop \
                        hello-make \
                        hello-cmake \
                        hello-cpp \
                        helloworld \
                        hello-autotools \
                        lz4 \
                        bbexample \
                        sayhello \
                        nano \
                        toptest \
                        timertest \
                        "